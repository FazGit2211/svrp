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

    public EnterpriseController(EnterpriseService enterpriseServ){
        _enterpriseService = enterpriseServ;
    }

    @PostMapping
    public ResponseEntity<Enterprise> createEnterprise(@RequestBody EnterpriseDto enterprise){
        try {
            Enterprise enterpriseCreated = _enterpriseService.createNew(enterprise);
            if (enterpriseCreated == null){
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Enterprise>> getAllEnterprises(){
        try {
            List<Enterprise> enterpriseList = _enterpriseService.getAll();
            if (enterpriseList.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(enterpriseList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enterprise> updateEnterpriseById(@RequestBody EnterpriseDto enterpriseDto,@PathVariable int id){
        try {
            Enterprise enterpriseUpdated = _enterpriseService.update(enterpriseDto, id);
            if (enterpriseUpdated == null){
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(enterpriseUpdated);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Enterprise> deleteEnterpriseById(@PathVariable int id){
        try {
            Enterprise enterpriseDeleted = _enterpriseService.delete(id);
            if (enterpriseDeleted == null){
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(enterpriseDeleted);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
