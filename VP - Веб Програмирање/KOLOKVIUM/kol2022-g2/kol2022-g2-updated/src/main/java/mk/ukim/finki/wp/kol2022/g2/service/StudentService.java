package mk.ukim.finki.wp.kol2022.g2.service;


import mk.ukim.finki.wp.kol2022.g2.model.Student;
import mk.ukim.finki.wp.kol2022.g2.model.StudentType;
import mk.ukim.finki.wp.kol2022.g2.model.exceptions.InvalidStudentIdException;

import java.time.LocalDate;
import java.util.List;

public interface StudentService {

    /**
     * @return List of all entities in the database
     */
    List<Student> listAll();

    /**
     * returns the entity with the given id
     *
     * @param id The id of the entity that we want to obtain
     * @return
     * @throws InvalidStudentIdException when there is no entity with the given id
     */
    Student findById(Long id);

    /**
     * This method is used to create a new entity, and save it in the database.
     *
     * @return The entity that is created. The id should be generated when the entity is created.
     * @throws InvalidCourseIdException when there is no category with the given id
     */
    Student create(String name, String email, String password, StudentType type, List<Long> courseId, LocalDate enrollmentDate);

    /**
     * This method is used to modify an entity, and save it in the database.
     *
     * @param id          The id of the entity that is being edited
     * @return The entity that is updated.
     * @throws InvalidStudentIdException when there is no entity with the given id
     * @throws InvalidCourseIdException when there is no course with the given id
     */
    Student update(Long id, String name, String email, String password, StudentType type, List<Long> coursesId, LocalDate enrollmentDate);

    /**
     * Method that should delete an entity. If the id is invalid, it should throw InvalidStudentIdException.
     *
     * @param id
     * @return The entity that is deleted.
     * @throws InvalidStudentIdException when there is no entity with the given id
     */
    Student delete(Long id);

    /**
     * The implementation of this method should use repository implementation for the filtering.
     * All arguments are nullable. When an argument is null, we should not filter by that attribute
     *
     * @return The entities that meet the filtering criteria
     */
    List<Student> filter(Long courseId, Integer yearsOfStudying);
}
