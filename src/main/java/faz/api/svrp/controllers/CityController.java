package faz.api.svrp.controllers;

import faz.api.svrp.models.City;
import faz.api.svrp.services.cityServices.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/citys", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController {

    @Autowired
    private final CityService _cityService;

    public CityController(CityService cityServ){
        _cityService = cityServ;
    }

    @PostMapping
    public ResponseEntity<City> CreateNew(@RequestBody City city){
        try {
            City cityCreated = _cityService.CreateNew(city);
            if (cityCreated == null){
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<City>> GetAll(){
        try {
            List<City> cityList = _cityService.GetAll();
            if (cityList.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(cityList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
