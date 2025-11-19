package faz.api.svrp.services.enterpriseServices;

import faz.api.svrp.models.Enterprise;
import faz.api.svrp.repositorys.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EnterpriseService implements EnterpriseInterface{

    @Autowired
    private final EnterpriseRepository _enterpriseRepository;

    public EnterpriseService(EnterpriseRepository enterpriseRepo){
        _enterpriseRepository = enterpriseRepo;
    }

    @Override
    public Enterprise CreateNew(Enterprise enterprise) {
        try {
            return _enterpriseRepository.save(enterprise);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Enterprise> GetAll() {
        try {
            return _enterpriseRepository.findAll();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
