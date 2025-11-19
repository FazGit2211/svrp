package faz.api.svrp.services.tourPackageServices;

import faz.api.svrp.models.TourPackage;
import faz.api.svrp.repositorys.TourPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TourPackageService implements TourPackageInterface{

    @Autowired
    private final TourPackageRepository _tourPackageRepository;

    public TourPackageService(TourPackageRepository tourPackageRepo){
        _tourPackageRepository = tourPackageRepo;
    }

    @Override
    public TourPackage createNew(TourPackage tourPackage) {
        try {
            return _tourPackageRepository.save(tourPackage);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TourPackage> getAll() {
        try {
            return _tourPackageRepository.findAll();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
