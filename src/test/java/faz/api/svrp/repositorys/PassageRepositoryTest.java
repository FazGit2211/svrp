package faz.api.svrp.repositorys;

import faz.api.svrp.models.Client;
import faz.api.svrp.models.Passage;
import faz.api.svrp.models.Person;
import faz.api.svrp.models.TourPackage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class PassageRepositoryTest {
    @Autowired
    private TourPackageRepository tourPackageRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PassageRepository passageRepository;

    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    @Transactional
    public void createPassage() {
        Optional<TourPackage> tourPackage = tourPackageRepository.findById(1);
        Optional<Client> clientExist = clientRepository.findById(1);

        Passage passage = new Passage("2026-02-02", "Tk383heh", "748", 1840);
        passage.setClient(clientExist.get());
        passage.setTourPackage(tourPackage.get());

        testEntityManager.persist(passage);
        System.out.println(passage.getClient());
        System.out.println(passage.getTourPackage());
    }

    @Test
    @Transactional
    public void deleteById() {
        Optional<Passage> passageExist = passageRepository.findById(1);
        assertEquals(passageExist.get(), passageExist.get());
    }

}