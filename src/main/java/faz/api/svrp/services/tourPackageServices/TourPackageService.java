package faz.api.svrp.services.tourPackageServices;

import faz.api.svrp.dtos.TourPackageDto;
import faz.api.svrp.models.DiscountPercentage;
import faz.api.svrp.models.Enterprise;
import faz.api.svrp.models.TourPackage;
import faz.api.svrp.repositorys.EnterpriseRepository;
import faz.api.svrp.repositorys.TourPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourPackageService implements TourPackageInterface {

    @Autowired
    private final TourPackageRepository _tourPackageRepository;

    @Autowired
    private final EnterpriseRepository _enterpriseRepository;

    public TourPackageService(TourPackageRepository tourPackageRepo, EnterpriseRepository enterpriseRepo) {
        _tourPackageRepository = tourPackageRepo;
        _enterpriseRepository = enterpriseRepo;
    }

    boolean emptyValueData = true;
    boolean emptyValueId = true;

    @Override
    public TourPackage createNew(TourPackageDto tourPackageDto) {
        try {
            emptyValueData = emptyValues(tourPackageDto);
            if (emptyValueData) {
                return null;
            }
            DiscountPercentage discountPercentage = new DiscountPercentage();
            double addDiscount = discountPercentage.calculateDiscount(tourPackageDto.getPrice(), 10, 21);
            TourPackage tourPackageNew = new TourPackage(addDiscount, tourPackageDto.getOriginCity(), tourPackageDto.getDestinyCity(), tourPackageDto.getTypeTransport());
            Optional<Enterprise> enterpriseExist = _enterpriseRepository.findById(tourPackageDto.getEnterpriseId());
            if (enterpriseExist.isPresent()) {
                tourPackageNew.setEnterprise(enterpriseExist.get());
            }
            return _tourPackageRepository.save(tourPackageNew);
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

    @Override
    public TourPackage update(TourPackageDto tourPackageDto, int id) {
        try {
            emptyValueData = emptyValues(tourPackageDto);
            emptyValueId = emptyId(id);
            if (emptyValueData || emptyValueId) {
                return null;
            }
            Optional<TourPackage> tourPackageExist = _tourPackageRepository.findById(id);
            if (tourPackageExist.isEmpty()) {
                return null;
            }
            TourPackage tourPackageUpdate = tourPackageExist.get();
            tourPackageUpdate.setPrice(tourPackageDto.getPrice());
            tourPackageUpdate.setOriginCity(tourPackageDto.getOriginCity());
            tourPackageUpdate.setDestinyCity(tourPackageDto.getDestinyCity());
            tourPackageUpdate.setTypeTransport(tourPackageDto.getTypeTransport());
            Optional<Enterprise> enterpriseExist = _enterpriseRepository.findById(tourPackageDto.getEnterpriseId());
            if (enterpriseExist.isPresent()){
                tourPackageUpdate.setEnterprise(enterpriseExist.get());
            }
            _tourPackageRepository.save(tourPackageUpdate);
            return tourPackageUpdate;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TourPackage delete(int id) {
        try {
            emptyValueId = emptyId(id);
            if (emptyValueId) {
                return null;
            }
            Optional<TourPackage> tourPackageExist = _tourPackageRepository.findById(id);
            if (tourPackageExist.isEmpty()) {
                return null;
            }
            _tourPackageRepository.delete(tourPackageExist.get());
            return tourPackageExist.get();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean emptyValues(TourPackageDto tourPackageDto) {
        if (tourPackageDto.getPrice() <= 0 || tourPackageDto.getOriginCity().trim().isBlank() || tourPackageDto.getDestinyCity().trim().isBlank() || tourPackageDto.getTypeTransport().trim().isBlank()) {
            return true;
        }
        return false;
    }

    private boolean emptyId(int id) {
        return id <= 0;
    }
}
