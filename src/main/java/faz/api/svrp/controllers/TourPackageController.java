package faz.api.svrp.controllers;

import faz.api.svrp.models.TourPackage;
import faz.api.svrp.services.tourPackageServices.TourPackageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/tourpackages", produces = MediaType.APPLICATION_JSON_VALUE)
public class TourPackageController {

    private final TourPackageService _tourPackageService;

    public TourPackageController(TourPackageService tourPackageServ){
        _tourPackageService = tourPackageServ;
    }

    @PostMapping
    public ResponseEntity<TourPackage> CreateNewTourPackage(@RequestBody TourPackage tourPackage){
        try {
            TourPackage tourPackageCreated = _tourPackageService.CreateNew(tourPackage);
            if (tourPackageCreated == null){
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TourPackage>> GetAllTourPackages(){
        try {
            List<TourPackage> tourPackageList = _tourPackageService.GetAll();
            if (tourPackageList.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(tourPackageList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
