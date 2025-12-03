package faz.api.svrp.controllers;

import faz.api.svrp.dtos.EnterpriseDto;
import faz.api.svrp.models.Enterprise;
import faz.api.svrp.services.enterpriseServices.EnterpriseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enterprises")
public class EnterpriseController {
    private final EnterpriseService _enterpriseService;

    public EnterpriseController(EnterpriseService enterpriseServ) {
        _enterpriseService = enterpriseServ;
    }

    @PostMapping
    public ResponseEntity<Enterprise> createEnterprise(@RequestBody EnterpriseDto enterprise) {
        _enterpriseService.createNew(enterprise);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<Enterprise>> getAllEnterprises() {
        List<Enterprise> enterpriseList = _enterpriseService.getAll();
        return ResponseEntity.ok(enterpriseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enterprise> updateEnterpriseById(@RequestBody EnterpriseDto enterpriseDto, @PathVariable int id) {
        Enterprise enterpriseUpdated = _enterpriseService.update(enterpriseDto, id);
        return ResponseEntity.ok(enterpriseUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Enterprise> deleteEnterpriseById(@PathVariable int id) {
        Enterprise enterpriseDeleted = _enterpriseService.delete(id);
        return ResponseEntity.ok(enterpriseDeleted);
    }
}
