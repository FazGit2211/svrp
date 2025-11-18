package faz.api.svrp.services.tourPackageServices;

import faz.api.svrp.models.TourPackage;

import java.util.List;

public interface TourPackageInterface {
    TourPackage CreateNew(TourPackage tourPackage);
    List<TourPackage> GetAll();
}
