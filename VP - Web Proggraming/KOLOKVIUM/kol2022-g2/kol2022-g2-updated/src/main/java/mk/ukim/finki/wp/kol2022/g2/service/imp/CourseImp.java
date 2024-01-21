package mk.ukim.finki.wp.kol2022.g2.service.imp;

import mk.ukim.finki.wp.kol2022.g2.model.Course;
import mk.ukim.finki.wp.kol2022.g2.model.exceptions.InvalidCourseIdException;
import mk.ukim.finki.wp.kol2022.g2.repository.CourseRepository;
import mk.ukim.finki.wp.kol2022.g2.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseImp implements CourseService {

    private final CourseRepository courseRepository;

    public CourseImp(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow(InvalidCourseIdException::new);
    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course create(String name) {
        return courseRepository.save(new Course(name));
    }
}
