package faz.api.svrp.services.passageServices;

import faz.api.svrp.models.Passage;

import java.util.List;

public interface PassageInterface {
    Passage createNew(Passage passage);
    List<Passage> getAll();
}
