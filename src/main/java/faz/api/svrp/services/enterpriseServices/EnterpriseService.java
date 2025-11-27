package faz.api.svrp.services.enterpriseServices;

import faz.api.svrp.dtos.EnterpriseDto;
import faz.api.svrp.models.Enterprise;
import faz.api.svrp.repositorys.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnterpriseService implements EnterpriseInterface {

    @Autowired
    private final EnterpriseRepository _enterpriseRepository;

    public EnterpriseService(EnterpriseRepository enterpriseRepo) {
        _enterpriseRepository = enterpriseRepo;
    }

    boolean emptyValueData = true;
    boolean emptyValueId = true;

    @Override
    public Enterprise createNew(EnterpriseDto enterprise) {
        try {
            emptyValueData = emptyValues(enterprise);
            if (emptyValueData) {
                return null;
            }
            Enterprise createNewEnterprise = new Enterprise(enterprise.getName(), enterprise.getAddress(), enterprise.getCuit());
            return _enterpriseRepository.save(createNewEnterprise);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Enterprise> getAll() {
        try {
            return _enterpriseRepository.findAll();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Enterprise update(EnterpriseDto enterpriseDto, int id) {
        try {
            emptyValueData = emptyValues(enterpriseDto);
            emptyValueId = emptyId(id);
            if (emptyValueData || emptyValueId) {
                return null;
            }
            Optional<Enterprise> enterpriseExist = _enterpriseRepository.findById(id);
            if (enterpriseExist.isEmpty()) {
                return null;
            }
            Enterprise enterpriseUpdate = enterpriseExist.get();
            enterpriseUpdate.setName(enterpriseDto.getName());
            enterpriseUpdate.setAddress(enterpriseDto.getAddress());
            enterpriseUpdate.setCuit(enterpriseDto.getCuit());
            return _enterpriseRepository.save(enterpriseUpdate);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Enterprise delete(int id) {
        try {
            emptyValueId = emptyId(id);
            Optional<Enterprise> enterpriseExist = _enterpriseRepository.findById(id);
            if (enterpriseExist.isEmpty() || emptyValueId) {
                return null;
            }
            _enterpriseRepository.delete(enterpriseExist.get());
            return enterpriseExist.get();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
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
