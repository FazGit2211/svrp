package faz.api.svrp.services.cityServices;

import faz.api.svrp.models.City;

import java.util.List;

public interface CityInterface {
    City CreateNew(City city);
    List<City> GetAll();
}
