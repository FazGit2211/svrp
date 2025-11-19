package faz.api.svrp.services.clientServices;

import faz.api.svrp.models.Client;

import java.util.List;

public interface ClientInterface {
    Client createNew(Client client);
    List<Client> getAll();
}
