package faz.api.svrp.services.cityServices;

import faz.api.svrp.models.City;
import faz.api.svrp.repositorys.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityService implements CityInterface{

    @Autowired
    private final CityRepository _cityRepository;

    public CityService(CityRepository cityRepo){
        _cityRepository = cityRepo;
    }

    @Override
    public City CreateNew(City city) {
        try {
            return _cityRepository.save(city);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<City> GetAll() {
        try {
            return _cityRepository.findAll();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
