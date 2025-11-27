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
        try {
            TourPackage tourPackageCreated = _tourPackageService.createNew(tourPackage);
            if (tourPackageCreated == null) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TourPackage>> getAllTourPackages() {
        try {
            List<TourPackage> tourPackageList = _tourPackageService.getAll();
            if (tourPackageList.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(tourPackageList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TourPackage> updateTourPackageById(@RequestBody TourPackageDto tourPackage, @PathVariable int id) {
        try {
            TourPackage tourPackageUpdated = _tourPackageService.update(tourPackage, id);
            if (tourPackageUpdated == null) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(tourPackageUpdated);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TourPackage> deletedTourPackageById(@PathVariable int id) {
        try {
            TourPackage tourPackageDeleted = _tourPackageService.delete(id);
            if (tourPackageDeleted == null) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(tourPackageDeleted);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
