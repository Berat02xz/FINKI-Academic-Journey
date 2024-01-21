package mk.ukim.finki.wp.kol2022.g1.service;


import mk.ukim.finki.wp.kol2022.g1.model.Employee;
import mk.ukim.finki.wp.kol2022.g1.model.EmployeeType;
import mk.ukim.finki.wp.kol2022.g1.model.exceptions.InvalidEmployeeIdException;
import mk.ukim.finki.wp.kol2022.g1.model.exceptions.InvalidSkillIdException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface EmployeeService {

    /**
     * @return List of all entities in the database
     */
    List<Employee> listAll();

    /**
     * returns the entity with the given id
     *
     * @param id The id of the entity that we want to obtain
     * @return
     * @throws InvalidEmployeeIdException when there is no entity with the given id
     */
    Employee findById(Long id);

    /**
     * This method is used to create a new entity, and save it in the database.
     *
     * @return The entity that is created. The id should be generated when the entity is created.
     * @throws InvalidSkillIdException when there is no category with the given id
     */
    Employee create(String name, String email, String password, EmployeeType type, List<Long> skillId, LocalDate employmentDate);

    /**
     * This method is used to modify an entity, and save it in the database.
     *
     * @param id          The id of the entity that is being edited
     * @return The entity that is updated.
     * @throws InvalidEmployeeIdException when there is no entity with the given id
     * @throws InvalidSkillIdException    when there is no category with the given id
     */
    Employee update(Long id, String name, String email, String password, EmployeeType type, List<Long> skillId, LocalDate employmentDate);

    /**
     * Method that should delete an entity. If the id is invalid, it should throw InvalidEmployeeIdException.
     *
     * @param id
     * @return The entity that is deleted.
     * @throws InvalidEmployeeIdException when there is no entity with the given id
     */
    Employee delete(Long id);

    /**
     * The implementation of this method should use repository implementation for the filtering.
     * All arguments are nullable. When an argument is null, we should not filter by that attribute
     *
     * @return The entities that meet the filtering criteria
     */
    List<Employee> filter(Long skillId, Integer yearsOfService);
}
