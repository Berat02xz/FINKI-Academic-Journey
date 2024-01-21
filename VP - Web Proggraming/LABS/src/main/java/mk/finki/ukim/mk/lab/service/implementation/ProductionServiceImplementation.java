package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.repository.impl.ProductionRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ProductionRepositoryJPA;
import mk.finki.ukim.mk.lab.service.ProductionInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionServiceImplementation implements ProductionInterface {

    public ProductionRepositoryJPA production;

    public ProductionServiceImplementation(ProductionRepositoryJPA production){
        this.production = production;
    }

    @Override
    public List<Production> findAll() {
        return production.findAll();
    }

    @Override
    public Production findById(Long id) {
        return production.findProductionById(id);
    }



}
