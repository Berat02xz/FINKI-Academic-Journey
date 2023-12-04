package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductionRepositoryJPA extends JpaRepository<Production,Long> {
    Production findProductionById(Long id);
    void deleteProductionById(Long id);
    List<Production> findAllBy();

}
