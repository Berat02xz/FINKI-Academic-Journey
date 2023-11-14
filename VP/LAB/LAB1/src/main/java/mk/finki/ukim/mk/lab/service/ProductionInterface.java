package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Production;

import java.util.List;


public interface ProductionInterface{
    List<Production> findAll();
    Production findById(Long id);

}
