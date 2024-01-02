package mk.ukim.finki.wp.kol2022.g2.repository;

import mk.ukim.finki.wp.kol2022.g2.model.Course;
import mk.ukim.finki.wp.kol2022.g2.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {


    List<Student> findAllByCoursesContainingAndEnrollmentDateBefore(Course course, LocalDate EnrollmentDate);

    List<Student> findAllByCoursesContaining(Course course);

    List<Student> findAllByEnrollmentDateBefore(LocalDate EnrollmentDate);
}
