package faz.api.svrp.services.tourPackageServices;

import faz.api.svrp.dtos.TourPackageDto;
import faz.api.svrp.exceptions.BadRequestException;
import faz.api.svrp.exceptions.NoContentException;
import faz.api.svrp.exceptions.ResourceNotFoundException;
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
        emptyValueData = emptyValues(tourPackageDto);
        if (emptyValueData) {
            throw new BadRequestException("Empty values.");
        }
        TourPackage tourPackageNew = new TourPackage(tourPackageDto.getPrice(), tourPackageDto.getOriginCity(), tourPackageDto.getDestinyCity(), tourPackageDto.getTypeTransport(), tourPackageDto.getDate(),tourPackageDto.getImageUrl());
        Optional<Enterprise> enterpriseExist = _enterpriseRepository.findById(tourPackageDto.getEnterpriseId());
        if (enterpriseExist.isPresent()) {
            tourPackageNew.setEnterprise(enterpriseExist.get());
        }
        return _tourPackageRepository.save(tourPackageNew);
    }

    @Override
    public List<TourPackage> getAll() {
        List<TourPackage> tourPackageList = _tourPackageRepository.findAll();
        if (tourPackageList.isEmpty() || tourPackageList == null) {
            throw new NoContentException("Empty passages");
        }
        return tourPackageList;
    }

    @Override
    public TourPackage update(TourPackageDto tourPackageDto, int id) {
        emptyValueData = emptyValues(tourPackageDto);
        emptyValueId = emptyId(id);
        if (emptyValueData || emptyValueId) {
            throw new BadRequestException("Empty values.");
        }
        Optional<TourPackage> tourPackageExist = _tourPackageRepository.findById(id);
        if (tourPackageExist.isEmpty()) {
            throw new ResourceNotFoundException("Tour package not exist");
        }
        TourPackage tourPackageUpdate = tourPackageExist.get();
        tourPackageUpdate.setPrice(tourPackageDto.getPrice());
        tourPackageUpdate.setOriginCity(tourPackageDto.getOriginCity());
        tourPackageUpdate.setDestinyCity(tourPackageDto.getDestinyCity());
        tourPackageUpdate.setTypeTransport(tourPackageDto.getTypeTransport());
        tourPackageUpdate.setDate(tourPackageDto.getDate());
        Optional<Enterprise> enterpriseExist = _enterpriseRepository.findById(tourPackageDto.getEnterpriseId());
        if (enterpriseExist.isPresent()) {
            tourPackageUpdate.setEnterprise(enterpriseExist.get());
        }
        _tourPackageRepository.save(tourPackageUpdate);
        return tourPackageUpdate;
    }

    @Override
    public TourPackage delete(int id) {

        emptyValueId = emptyId(id);
        if (emptyValueId) {
            throw new BadRequestException("Empty values.");
        }
        Optional<TourPackage> tourPackageExist = _tourPackageRepository.findById(id);
        if (tourPackageExist.isEmpty()) {
            throw new ResourceNotFoundException("Tour package not exist");
        }
        _tourPackageRepository.delete(tourPackageExist.get());
        return tourPackageExist.get();
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
