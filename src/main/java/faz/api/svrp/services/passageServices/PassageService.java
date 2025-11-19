package faz.api.svrp.services.passageServices;

import faz.api.svrp.models.Passage;
import faz.api.svrp.repositorys.PassageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PassageService implements PassageInterface{
    @Autowired
    private final PassageRepository _passageRepository;

    public PassageService(PassageRepository passageRepo){
        _passageRepository = passageRepo;
    }

    @Override
    public Passage createNew(Passage passage) {
        try{
            return _passageRepository.save(passage);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Passage> getAll() {
        try{
            return _passageRepository.findAll();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
