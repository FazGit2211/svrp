package faz.api.svrp.controllers;

import faz.api.svrp.dtos.ClientDto;
import faz.api.svrp.models.Client;
import faz.api.svrp.services.clientServices.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private final ClientService _clientService;

    public ClientController(ClientService clientServ){
        _clientService = clientServ;
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody ClientDto client){
        try {
            Client clientCreated = _clientService.createNew(client);
            if (clientCreated == null){
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients(){
        try {
            List<Client> clientList = _clientService.getAll();
            if (clientList.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(clientList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClientById(@RequestBody ClientDto client,@PathVariable int id){
        try{
            Client clientUpdated = _clientService.update(client,id);
            if (clientUpdated == null){
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(clientUpdated);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClientById(@PathVariable int id){
        try {
            Client clientDeleted = _clientService.deleteById(id);
            if (clientDeleted == null){
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(clientDeleted);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
