package faz.api.svrp.controllers;

import faz.api.svrp.models.Enterprise;
import faz.api.svrp.services.enterpriseServices.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/enterprises", produces = MediaType.APPLICATION_JSON_VALUE)
public class EnterpriseController {

    @Autowired
    private final EnterpriseService _enterpriseService;

    public EnterpriseController(EnterpriseService enterpriseServ){
        _enterpriseService = enterpriseServ;
    }

    @PostMapping
    public ResponseEntity<Enterprise> CreateNewEnterprise(@RequestBody Enterprise enterprise){
        try {
            Enterprise enterpriseCreated = _enterpriseService.CreateNew(enterprise);
            if (enterpriseCreated == null){
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Enterprise>> GetAllEnterprise(){
        try {
            List<Enterprise> enterpriseList = _enterpriseService.GetAll();
            if (enterpriseList.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(enterpriseList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
