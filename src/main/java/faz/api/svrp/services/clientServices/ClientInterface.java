package faz.api.svrp.services.clientServices;

import faz.api.svrp.dtos.ClientDto;
import faz.api.svrp.models.Client;

import java.util.List;

public interface ClientInterface {
    Client createNew(ClientDto clientDto);
    List<Client> getAll();
    Client update(ClientDto clientDto, int id);
    Client deleteById(int id);
}
