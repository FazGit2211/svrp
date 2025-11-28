package faz.api.svrp.services.clientServices;

import faz.api.svrp.dtos.ClientDto;
import faz.api.svrp.models.Client;
import faz.api.svrp.repositorys.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements ClientInterface {

    @Autowired
    private final ClientRepository _clientRepository;

    public ClientService(ClientRepository clientRepo) {
        _clientRepository = clientRepo;
    }

    boolean emptyValueData = true;
    boolean emptyValueId = true;

    @Override
    public Client createNew(ClientDto clientDto) {
        try {
            emptyValueData = emptyValues(clientDto);
            if (emptyValueData) {
                return null;
            }
            Client createNewClient = new Client(clientDto.getName(), clientDto.getSurname(), clientDto.getDocumentNumber(), clientDto.getBirthdate(), clientDto.getAddress(), clientDto.getEmail());
            return _clientRepository.save(createNewClient);
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

    @Override
    public Client update(ClientDto clientDto, int id) {
        try {
            emptyValueData = emptyValues(clientDto);
            emptyValueId = emptyId(id);
            if (emptyValueData || emptyValueId) {
                return null;
            }
            Optional<Client> clientExist = _clientRepository.findById(id);
            if (clientExist.isEmpty()) {
                return null;
            }
            Client client = clientExist.get();
            client.setName(clientDto.getName());
            client.setSurname(clientDto.getSurname());
            client.setDocumentNumber(clientDto.getDocumentNumber());
            client.setBirthdate(clientDto.getBirthdate());
            client.setAddress(clientDto.getAddress());
            client.setEmail(clientDto.getEmail());
            return _clientRepository.save(client);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Client deleteById(int id) {
        try {
            emptyValueId = emptyId(id);
            if (emptyValueId) {
                return null;
            }
            Optional<Client> clientExist = _clientRepository.findById(id);
            if (clientExist.isEmpty()) {
                return null;
            }
            Client clientDelete = clientExist.get();
            _clientRepository.deleteById(id);
            return clientDelete;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean emptyValues(ClientDto clientDto) {
        if (clientDto.getName().trim().isBlank() || clientDto.getSurname().trim().isBlank() || clientDto.getAddress().trim().isBlank() || clientDto.getBirthdate().trim().isBlank() || clientDto.getDocumentNumber() <= 0 || clientDto.getEmail().trim().isBlank()) {
            return true;
        }
        return false;
    }

    private boolean emptyId(int id) {
        return id <= 0;
    }
}
