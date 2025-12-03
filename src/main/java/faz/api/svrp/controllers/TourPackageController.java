package faz.api.svrp.controllers;

import faz.api.svrp.dtos.TourPackageDto;
import faz.api.svrp.models.TourPackage;
import faz.api.svrp.services.tourPackageServices.TourPackageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tourpackages")
public class TourPackageController {

    private final TourPackageService _tourPackageService;

    public TourPackageController(TourPackageService tourPackageServ) {
        _tourPackageService = tourPackageServ;
    }

    @PostMapping
    public ResponseEntity<TourPackage> createTourPackage(@RequestBody TourPackageDto tourPackage) {
        _tourPackageService.createNew(tourPackage);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<TourPackage>> getAllTourPackages() {
        List<TourPackage> tourPackageList = _tourPackageService.getAll();
        return ResponseEntity.ok(tourPackageList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TourPackage> updateTourPackageById(@RequestBody TourPackageDto tourPackage, @PathVariable int id) {
        TourPackage tourPackageUpdated = _tourPackageService.update(tourPackage, id);
        return ResponseEntity.ok(tourPackageUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TourPackage> deletedTourPackageById(@PathVariable int id) {
        TourPackage tourPackageDeleted = _tourPackageService.delete(id);
        return ResponseEntity.ok(tourPackageDeleted);
    }

}
