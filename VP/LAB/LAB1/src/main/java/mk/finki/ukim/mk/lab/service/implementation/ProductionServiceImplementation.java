package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.repository.ProductionRepository;
import mk.finki.ukim.mk.lab.service.ProductionInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductionServiceImplementation implements ProductionInterface {

    public ProductionRepository production;

    public ProductionServiceImplementation(ProductionRepository production){
        this.production = production;
    }

    @Override
    public List<Production> findAll() {
        return production.findAll();
    }

    @Override
    public Production findById(Long id) {
        return production.findById(id);
    }



}
