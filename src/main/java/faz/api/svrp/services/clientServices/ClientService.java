package faz.api.svrp.services.clientServices;

import faz.api.svrp.models.Client;
import faz.api.svrp.repositorys.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClientService implements ClientInterface{

    @Autowired
    private final ClientRepository _clientRepository;

    public ClientService(ClientRepository clientRepo){
        _clientRepository = clientRepo;
    }

    @Override
    public Client createNew(Client client) {
        try {
            return _clientRepository.save(client);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Client> getAll() {
        try {
            return _clientRepository.findAll();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
