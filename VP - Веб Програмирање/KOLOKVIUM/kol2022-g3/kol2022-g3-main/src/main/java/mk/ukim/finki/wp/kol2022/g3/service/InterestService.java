package mk.ukim.finki.wp.kol2022.g3.service;

import mk.ukim.finki.wp.kol2022.g3.model.Interest;
import mk.ukim.finki.wp.kol2022.g3.model.exceptions.InvalidInterestIdException;

import java.util.List;

public interface InterestService {

    /**
     * returns the entity with the given id
     *
     * @param id The id of the entity that we want to obtain
     * @return
     * @throws InvalidInterestIdException when there is no entity with the given id
     */
    Interest findById(Long id);

    /**
     * @return List of all entities in the database
     */
    List<Interest> listAll();

    /**
     * This method is used to create a new entity, and save it in the database.
     *
     * @param name
     * @return The entity that is created. The id should be generated when the entity is created.
     */
    Interest create(String name);
}
