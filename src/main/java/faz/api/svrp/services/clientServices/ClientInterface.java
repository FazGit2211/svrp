package faz.api.svrp.services.clientServices;

import faz.api.svrp.models.Client;

import java.util.List;

public interface ClientInterface {
    Client CreateNew(Client client);
    List<Client> GetAll();
}
