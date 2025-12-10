package faz.api.svrp.services.passageServices;

import faz.api.svrp.dtos.PassageDto;
import faz.api.svrp.models.Passage;

import java.util.List;

public interface PassageInterface {
    Passage createNewPassage(PassageDto passage);

    List<Passage> getAllPassages();

    Passage updatePassage(PassageDto passageDto, int id);

    Passage deletePassage(int id);

    Passage findPassageById(int id);
}
