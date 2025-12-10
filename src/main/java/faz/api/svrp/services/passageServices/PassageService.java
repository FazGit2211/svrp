package faz.api.svrp.services.passageServices;

import faz.api.svrp.dtos.PassageDto;
import faz.api.svrp.exceptions.BadRequestException;
import faz.api.svrp.exceptions.NoContentException;
import faz.api.svrp.exceptions.ResourceNotFoundException;
import faz.api.svrp.models.Client;
import faz.api.svrp.models.Discount;
import faz.api.svrp.models.Passage;
import faz.api.svrp.models.TourPackage;
import faz.api.svrp.repositorys.ClientRepository;
import faz.api.svrp.repositorys.PassageRepository;
import faz.api.svrp.repositorys.TourPackageRepository;
import jakarta.persistence.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PassageService implements PassageInterface {
    private final PassageRepository _passageRepository;
    private final ClientRepository _clientRepository;
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
        emptyValueData = emptyValues(passage);
        if (emptyValueData) {
            throw new BadRequestException("Empty values.");
        }
        //Aplicar descuento
        Discount.iva = 21;
        Discount.percentageDiscount = 10;
        float priceWithDiscount = Discount.calculateDiscount(passage.getTotalPrice());
        Passage passageNew = new Passage(passage.getDate(), passage.getTicketNumber(), passage.getSeatNumber(), priceWithDiscount);
        Optional<Client> clientExist = _clientRepository.findById(passage.getClientId());
        if (clientExist.isEmpty()) {
            throw new BadRequestException("Empty client.");
        }
        Optional<TourPackage> tourPackageExist = _tourPackageRepository.findById(passage.getTourpackageId());
        if (tourPackageExist.isEmpty()) {
            throw new BadRequestException("Empty tour package.");
        }
        passageNew.setClient(clientExist.get());
        passageNew.setTourPackage(tourPackageExist.get());
        return _passageRepository.save(passageNew);
    }

    @Override
    public List<Passage> getAllPassages() {
        List<Passage> passageList = _passageRepository.findAll();
        if (passageList.isEmpty() || passageList == null) {
            throw new NoContentException("Empty passages");
        }
        return passageList;
    }

    @Override
    public Passage updatePassage(PassageDto passage, int id) {
        emptyValueData = emptyValues(passage);
        emptyValueId = emptyId(id);
        if (emptyValueData || emptyValueId) {
            throw new BadRequestException("Empty values.");
        }
        Optional<Passage> passageExist = _passageRepository.findById(id);
        if (passageExist.isEmpty()) {
            throw new ResourceNotFoundException("Passage not exist");
        }
        Passage passageUpdate = passageExist.get();
        passageUpdate.setDate(passage.getDate());
        passageUpdate.setSeatNumber(passage.getSeatNumber());
        passageUpdate.setTicketNumber(passage.getTicketNumber());
        passageUpdate.setTotalPrice(passage.getTotalPrice());
        _passageRepository.save(passageUpdate);
        return passageUpdate;
    }

    @Override
    @Transactional
    public Passage deletePassage(int id) {
        emptyValueId = emptyId(id);
        if (emptyValueId) {
            throw new BadRequestException("Empty values.");
        }
        Optional<Passage> passageExist = _passageRepository.findById(id);
        if (passageExist.isEmpty()) {
            throw new ResourceNotFoundException("Passage not exist");
        }
        _passageRepository.delete(passageExist.get());
        return passageExist.get();
    }

    @Override
    public Passage findPassageById(int id) {
        emptyValueId = emptyId(id);
        if (emptyValueId) {
            throw new BadRequestException("Empty values.");
        }
        Optional<Passage> passageExist = _passageRepository.findById(id);
        if (passageExist.isEmpty()) {
            throw new ResourceNotFoundException("Passage not exist");
        }
        return passageExist.get();
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
