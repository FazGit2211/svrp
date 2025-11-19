package faz.api.svrp.controllers;

import faz.api.svrp.models.Client;
import faz.api.svrp.services.clientServices.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
    @Autowired
    private final ClientService _clientService;

    public ClientController(ClientService clientServ){
        _clientService = clientServ;
    }

    @PostMapping
    public ResponseEntity<Client> Create(@RequestBody Client client){
        try {
            Client clientCreated = _clientService.CreateNew(client);
            if (clientCreated == null){
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Client>> GetAllClients(){
        try {
            List<Client> clientList = _clientService.GetAll();
            if (clientList.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(clientList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
