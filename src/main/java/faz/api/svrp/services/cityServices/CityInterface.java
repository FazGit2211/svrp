package faz.api.svrp.services.cityServices;

import faz.api.svrp.models.City;

import java.util.List;

public interface CityInterface {
    City createNew(City city);
    List<City> getAll();
}
