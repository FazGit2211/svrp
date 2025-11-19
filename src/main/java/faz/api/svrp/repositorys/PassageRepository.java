package faz.api.svrp.repositorys;

import faz.api.svrp.models.Passage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassageRepository extends JpaRepository<Passage,Integer> {
}
