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
        _passageService.createNewPassage(passage);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<Passage>> getAllPassages() {
        List<Passage> passageList = _passageService.getAllPassages();
        return ResponseEntity.ok(passageList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passage> updatePassageById(@RequestBody PassageDto passage, @PathVariable int id) {
        Passage passageUpdated = _passageService.updatePassage(passage, id);
        return ResponseEntity.ok(passageUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Passage> deletePassageById(@PathVariable int id) {
        Passage passageDeleted = _passageService.deletePassage(id);
        return ResponseEntity.ok(passageDeleted);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passage> findPassageById(@PathVariable int id) {
        Passage passageExist = _passageService.findPassageById(id);
        return ResponseEntity.ok(passageExist);
    }
}
