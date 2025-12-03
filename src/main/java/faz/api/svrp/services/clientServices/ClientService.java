package faz.api.svrp.services.clientServices;

import faz.api.svrp.dtos.ClientDto;
import faz.api.svrp.exceptions.BadRequestException;
import faz.api.svrp.exceptions.InternalServerErrorException;
import faz.api.svrp.exceptions.NoContentException;
import faz.api.svrp.exceptions.ResourceNotFoundException;
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
        emptyValueData = emptyValues(clientDto);
        if (emptyValueData) {
            throw new BadRequestException("Empty values.");
        }
        Client createNewClient = new Client(clientDto.getName(), clientDto.getSurname(), clientDto.getDocumentNumber(), clientDto.getBirthdate(), clientDto.getAddress(), clientDto.getEmail());
        return _clientRepository.save(createNewClient);
    }

    @Override
    public List<Client> getAll() {

        List<Client> clients = _clientRepository.findAll();
        if (clients.isEmpty()) {
            throw new NoContentException("Empty clients");
        }
        return clients;
    }

    @Override
    public Client update(ClientDto clientDto, int id) {
        emptyValueData = emptyValues(clientDto);
        emptyValueId = emptyId(id);
        if (emptyValueData || emptyValueId) {
            throw new BadRequestException("Empty values;");
        }
        Optional<Client> clientExist = _clientRepository.findById(id);
        if (clientExist.isEmpty() || clientExist == null) {
            throw new ResourceNotFoundException("Client not exist");
        }
        Client client = clientExist.get();
        client.setName(clientDto.getName());
        client.setSurname(clientDto.getSurname());
        client.setDocumentNumber(clientDto.getDocumentNumber());
        client.setBirthdate(clientDto.getBirthdate());
        client.setAddress(clientDto.getAddress());
        client.setEmail(clientDto.getEmail());
        return _clientRepository.save(client);
    }

    @Override
    public Client deleteById(int id) {
        emptyValueId = emptyId(id);
        if (emptyValueId) {
            throw new BadRequestException("Empty values.");
        }
        Optional<Client> clientExist = _clientRepository.findById(id);
        if (clientExist.isEmpty()) {
            throw new ResourceNotFoundException("Client not exist");
        }
        Client clientDelete = clientExist.get();
        _clientRepository.deleteById(id);
        return clientDelete;
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
