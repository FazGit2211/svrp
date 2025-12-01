package faz.api.svrp.controllers;

import faz.api.svrp.dtos.ClientDto;
import faz.api.svrp.models.Client;
import faz.api.svrp.services.clientServices.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService _clientService;

    public ClientController(ClientService clientServ) {
        _clientService = clientServ;
    }

    @PostMapping
    public ResponseEntity<String> createClient(@RequestBody ClientDto client) {
        Client clientCreated = _clientService.createNew(client);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clientList = _clientService.getAll();
        return ResponseEntity.ok(clientList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClientById(@RequestBody ClientDto client, @PathVariable int id) {
        Client clientUpdated = _clientService.update(client, id);
        return ResponseEntity.ok(clientUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClientById(@PathVariable int id) {
        Client clientDeleted = _clientService.deleteById(id);
        return ResponseEntity.ok(clientDeleted);
    }
}
