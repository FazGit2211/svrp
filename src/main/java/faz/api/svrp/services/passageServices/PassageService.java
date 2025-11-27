package faz.api.svrp.services.passageServices;

import faz.api.svrp.dtos.PassageDto;
import faz.api.svrp.models.Client;
import faz.api.svrp.models.Passage;
import faz.api.svrp.models.TourPackage;
import faz.api.svrp.repositorys.ClientRepository;
import faz.api.svrp.repositorys.PassageRepository;
import faz.api.svrp.repositorys.TourPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassageService implements PassageInterface {

    @Autowired
    private final PassageRepository _passageRepository;

    @Autowired
    private final ClientRepository _clientRepository;

    @Autowired
    private final TourPackageRepository _tourPackageRepository;

    public PassageService(PassageRepository passageRepo, ClientRepository clientRepo, TourPackageRepository tourPackageRepo) {
        _passageRepository = passageRepo;
        _clientRepository = clientRepo;
        _tourPackageRepository = tourPackageRepo;
    }

    boolean emptyValueData = true;
    boolean emptyValueId = true;

    @Override
    public Passage createNewPassage(PassageDto passage) {
        try {
            emptyValueData = emptyValues(passage);
            if (emptyValueData) {
                return null;
            }
            Optional<Client> clientExist = _clientRepository.findById(passage.getClientId());
            if (clientExist.isEmpty()) {
                return null;
            }
            Optional<TourPackage> tourPackageExist = _tourPackageRepository.findById(passage.getTourpackageId());
            if (tourPackageExist.isEmpty()) {
                return null;
            }
            Passage passageNew = new Passage(passage.getDate(), passage.getTicketNumber(), passage.getSeatNumber());
            passageNew.setClient(clientExist.get());
            passageNew.setTourPackage(tourPackageExist.get());
            return _passageRepository.save(passageNew);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Passage> getAllPassages() {
        try {
            return _passageRepository.findAll();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Passage updatePassage(PassageDto passage, int id) {
        try {
            emptyValueData = emptyValues(passage);
            emptyValueId = emptyId(id);
            if (emptyValueData || emptyValueId) {
                return null;
            }
            Optional<Passage> passageExist = _passageRepository.findById(id);
            if (passageExist.isEmpty()) {
                return null;
            }
            Passage passageUpdate = passageExist.get();
            passageUpdate.setDate(passage.getDate());
            passageUpdate.setSeatNumber(passage.getSeatNumber());
            passageUpdate.setTicketNumber(passage.getTicketNumber());
            _passageRepository.save(passageUpdate);
            return passageUpdate;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Passage deletePassage(int id) {
        try {
            emptyValueId = emptyId(id);
            if (emptyValueId) {
                return null;
            }
            Optional<Passage> passageExist = _passageRepository.findById(id);
            if (passageExist.isEmpty()) {
                return null;
            }
            _passageRepository.delete(passageExist.get());
            return passageExist.get();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean emptyValues(PassageDto passageDto) {
        if (passageDto.getDate().trim().isBlank() || passageDto.getSeatNumber().trim().isBlank() || passageDto.getTicketNumber().trim().isBlank()) {
            return true;
        }
        return false;
    }

    private boolean emptyId(int id) {
        return id <= 0;
    }
}
