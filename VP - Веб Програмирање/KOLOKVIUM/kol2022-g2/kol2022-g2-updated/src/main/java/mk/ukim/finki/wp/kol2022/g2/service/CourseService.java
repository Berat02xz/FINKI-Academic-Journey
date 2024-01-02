package mk.ukim.finki.wp.kol2022.g2.service;

import mk.ukim.finki.wp.kol2022.g2.model.Course;
import mk.ukim.finki.wp.kol2022.g2.model.exceptions.InvalidCourseIdException;

import java.util.List;

public interface CourseService {

    /**
     * returns the entity with the given id
     *
     * @param id The id of the entity that we want to obtain
     * @return
     * @throws InvalidCourseIdException when there is no  course with the given id
     */
    Course findById(Long id);

    /**
     * @return List of all entities in the database
     */
    List<Course> listAll();

    /**
     * This method is used to create a new entity, and save it in the database.
     *
     * @param name
     * @return The entity that is created. The id should be generated when the entity is created.
     */
    Course create(String name);
}
