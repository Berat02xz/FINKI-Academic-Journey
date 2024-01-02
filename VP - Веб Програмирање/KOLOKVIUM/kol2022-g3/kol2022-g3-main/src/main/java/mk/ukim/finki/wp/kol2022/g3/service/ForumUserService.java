package mk.ukim.finki.wp.kol2022.g3.service;

import mk.ukim.finki.wp.kol2022.g3.model.ForumUser;
import mk.ukim.finki.wp.kol2022.g3.model.ForumUserType;
import mk.ukim.finki.wp.kol2022.g3.model.exceptions.InvalidForumUserIdException;
import mk.ukim.finki.wp.kol2022.g3.model.exceptions.InvalidInterestIdException;

import java.time.LocalDate;
import java.util.List;

public interface ForumUserService {

    /**
     * @return List of all entities in the database
     */
    List<ForumUser> listAll();

    /**
     * returns the entity with the given id
     *
     * @param id The id of the entity that we want to obtain
     * @return
     * @throws InvalidForumUserIdException when there is no entity with the given id
     */
    ForumUser findById(Long id);

    /**
     * This method is used to create a new entity, and save it in the database.
     *
     * @return The entity that is created. The id should be generated when the entity is created.
     * @throws InvalidInterestIdException when there is no entity with the given id
     */
    ForumUser create(String name, String email, String password, ForumUserType type, List<Long> interestId, LocalDate birthday);

    /**
     * This method is used to modify an entity, and save it in the database.
     *
     * @param id The id of the entity that is being edited
     * @return The entity that is updated.
     * @throws InvalidForumUserIdException when there is no entity with the given id
     * @throws InvalidInterestIdException  when there is no entity with the given id
     */
    ForumUser update(Long id, String name, String email, String password, ForumUserType type, List<Long> interestId, LocalDate birthday);

    /**
     * Method that should delete an entity. If the id is invalid, it should throw InvalidForumUserIdException.
     *
     * @param id
     * @return The entity that is deleted.
     * @throws InvalidForumUserIdException when there is no entity with the given id
     */
    ForumUser delete(Long id);

    /**
     * The implementation of this method should use repository implementation for the filtering.
     * All arguments are nullable. When an argument is null, we should not filter by that attribute
     *
     * @return The entities that meet the filtering criteria
     */
    List<ForumUser> filter(Long interestId, Integer age);
}
