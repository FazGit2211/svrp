package faz.api.svrp.services.tourPackageServices;

import faz.api.svrp.dtos.TourPackageDto;
import faz.api.svrp.models.TourPackage;

import java.util.List;

public interface TourPackageInterface {
    TourPackage createNew(TourPackageDto tourPackageDto);

    List<TourPackage> getAll();

    TourPackage update(TourPackageDto tourPackageDto, int id);

    TourPackage delete(int id);
}
