package faz.api.svrp.services.enterpriseServices;

import faz.api.svrp.dtos.EnterpriseDto;
import faz.api.svrp.exceptions.BadRequestException;
import faz.api.svrp.exceptions.NoContentException;
import faz.api.svrp.exceptions.ResourceNotFoundException;
import faz.api.svrp.models.Enterprise;
import faz.api.svrp.repositorys.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnterpriseService implements EnterpriseInterface {
    private final EnterpriseRepository _enterpriseRepository;

    public EnterpriseService(EnterpriseRepository enterpriseRepo) {
        _enterpriseRepository = enterpriseRepo;
    }

    boolean emptyValueData = true;
    boolean emptyValueId = true;

    @Override
    public Enterprise createNew(EnterpriseDto enterprise) {
        emptyValueData = emptyValues(enterprise);
        if (emptyValueData) {
            throw new BadRequestException("Empty values.");
        }
        Enterprise createNewEnterprise = new Enterprise(enterprise.getName(), enterprise.getAddress(), enterprise.getCuit());
        return _enterpriseRepository.save(createNewEnterprise);
    }

    @Override
    public List<Enterprise> getAll() {
        List<Enterprise> enterpriseList = _enterpriseRepository.findAll();
        if (enterpriseList.isEmpty() || enterpriseList == null) {
            throw new NoContentException("Empty enterprises");
        }
        return enterpriseList;
    }

    @Override
    public Enterprise update(EnterpriseDto enterpriseDto, int id) {
        emptyValueData = emptyValues(enterpriseDto);
        emptyValueId = emptyId(id);
        if (emptyValueData || emptyValueId) {
            throw new BadRequestException("Empty values.");
        }
        Optional<Enterprise> enterpriseExist = _enterpriseRepository.findById(id);
        if (enterpriseExist.isEmpty()) {
            throw new ResourceNotFoundException("Enterprise not exist");
        }
        Enterprise enterpriseUpdate = enterpriseExist.get();
        enterpriseUpdate.setName(enterpriseDto.getName());
        enterpriseUpdate.setAddress(enterpriseDto.getAddress());
        enterpriseUpdate.setCuit(enterpriseDto.getCuit());
        return _enterpriseRepository.save(enterpriseUpdate);
    }

    @Override
    public Enterprise delete(int id) {
        emptyValueId = emptyId(id);
        if (emptyValueId) {
            throw new BadRequestException("Empty values.");
        }
        Optional<Enterprise> enterpriseExist = _enterpriseRepository.findById(id);
        if (enterpriseExist.isEmpty()) {
            throw new ResourceNotFoundException("Enterprise not exist");
        }
        _enterpriseRepository.delete(enterpriseExist.get());
        return enterpriseExist.get();
    }

    private boolean emptyValues(EnterpriseDto enterpriseDto) {
        if (enterpriseDto.getName().trim().isBlank() || enterpriseDto.getAddress().trim().isBlank() || enterpriseDto.getCuit().trim().isBlank()) {
            return true;
        }
        return false;
    }

    private boolean emptyId(int id) {
        return id <= 0;
    }
}
