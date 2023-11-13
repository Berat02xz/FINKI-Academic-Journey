package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Production;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductionRepository {

    List<Production> prod;
    public ProductionRepository(){
        prod = new ArrayList<>();
        prod.add(new Production("Disney","USA","Los Angeles"));
        prod.add(new Production("Warner Bros","USA","Los Angeles"));
        prod.add(new Production("Universal Pictures","USA","Los Angeles"));
        prod.add(new Production("Paramount Pictures","USA","Los Angeles"));
        prod.add(new Production("A24","USA","Los Angeles"));

    }

    public List<Production> findAll() {
        return prod;
    }

    public Production findById(Long id) {
        return prod.get(Math.toIntExact(id));
    }
}
