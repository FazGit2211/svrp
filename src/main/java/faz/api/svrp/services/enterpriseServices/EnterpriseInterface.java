package faz.api.svrp.services.enterpriseServices;

import faz.api.svrp.dtos.EnterpriseDto;
import faz.api.svrp.models.Enterprise;

import java.util.List;

public interface EnterpriseInterface {
    Enterprise createNew(EnterpriseDto enterpriseDto);

    List<Enterprise> getAll();

    Enterprise update(EnterpriseDto enterpriseDto, int id);

    Enterprise delete(int id);
}
