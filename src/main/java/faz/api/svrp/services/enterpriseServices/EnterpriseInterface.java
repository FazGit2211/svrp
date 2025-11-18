package faz.api.svrp.services.enterpriseServices;

import faz.api.svrp.models.Enterprise;

import java.util.List;

public interface EnterpriseInterface {
    Enterprise CreateNew(Enterprise enterprise);
    List<Enterprise> GetAll();
}
