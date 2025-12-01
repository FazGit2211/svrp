package faz.api.svrp.controllers;

import faz.api.svrp.dtos.PassageDto;
import faz.api.svrp.models.Passage;
import faz.api.svrp.services.passageServices.PassageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passages")
public class PassageController {
    private final PassageService _passageService;

    public PassageController(PassageService passageServ) {
        _passageService = passageServ;
    }

    @PostMapping
    public ResponseEntity<Passage> createPassage(@RequestBody PassageDto passage) {
        try {
            Passage passageCreated = _passageService.createNewPassage(passage);
            if (passageCreated == null) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Passage>> getAllPassages() {
        try {
            List<Passage> passageList = _passageService.getAllPassages();
            if (passageList.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(passageList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passage> updatePassageById(@RequestBody PassageDto passage, @PathVariable int id) {
        try {
            Passage passageUpdated = _passageService.updatePassage(passage, id);
            if (passageUpdated == null) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(passageUpdated);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Passage> deletePassageById(@PathVariable int id) {
        try {
            Passage passageDeleted = _passageService.deletePassage(id);
            if (passageDeleted == null) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(passageDeleted);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
