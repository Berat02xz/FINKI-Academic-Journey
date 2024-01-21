package mk.ukim.finki.wp.kol2022.g2.service.imp;

import mk.ukim.finki.wp.kol2022.g2.model.Course;
import mk.ukim.finki.wp.kol2022.g2.model.Student;
import mk.ukim.finki.wp.kol2022.g2.model.StudentType;
import mk.ukim.finki.wp.kol2022.g2.model.exceptions.InvalidStudentIdException;
import mk.ukim.finki.wp.kol2022.g2.repository.CourseRepository;
import mk.ukim.finki.wp.kol2022.g2.repository.StudentRepository;
import mk.ukim.finki.wp.kol2022.g2.service.StudentService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentImp implements StudentService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentImp(CourseRepository courseRepository, StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(InvalidStudentIdException::new);
    }

    @Override
    public Student create(String name, String email, String password, StudentType type, List<Long> courseId, LocalDate enrollmentDate) {

        List<Course> courses = new ArrayList<>();

        for(int i=0;i<courseId.size();i++){
        Long id = courseId.get(i);
        courses.add(courseRepository.findById(id).orElseThrow(InvalidStudentIdException::new));
        }

        return studentRepository.save(new Student(name, email, passwordEncoder.encode(password), type, courses, enrollmentDate));
    }

    @Override
    public Student update(Long id, String name, String email, String password, StudentType type, List<Long> coursesId, LocalDate enrollmentDate) {

        Student student = studentRepository.findById(id).orElseThrow(InvalidStudentIdException::new);
        student.setName(name);
        student.setEmail(email);
        student.setPassword(passwordEncoder.encode(password));
        student.setType(type);
        student.setEnrollmentDate(enrollmentDate);

        List<Course> courses = new ArrayList<>();

        for(int i=0;i<coursesId.size();i++){
            Long idr = coursesId.get(i);
            courses.add(courseRepository.findById(idr).orElseThrow(InvalidStudentIdException::new));
        }

        student.setCourses(courses);

        return studentRepository.save(student);
    }

    @Override
    public Student delete(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(InvalidStudentIdException::new);
        studentRepository.delete(student);
        return student;
    }

    @Override
    public List<Student> filter(Long courseId, Integer yearsOfStudying) {

        if (courseId != null && yearsOfStudying != null) {
            Course course = courseRepository.findById(courseId).orElseThrow(InvalidStudentIdException::new);
            LocalDate enrollmentDate = LocalDate.now().minusYears(yearsOfStudying);
            return studentRepository.findAllByCoursesContainingAndEnrollmentDateBefore(course, enrollmentDate);
        } else if (courseId == null && yearsOfStudying == null) {
            return studentRepository.findAll();
        } else if (courseId != null) {
            Course course = courseRepository.findById(courseId).orElseThrow(InvalidStudentIdException::new);
            return studentRepository.findAllByCoursesContaining(course);
        } else {
            LocalDate enrollmentDate = LocalDate.now().minusYears(yearsOfStudying);
            return studentRepository.findAllByEnrollmentDateBefore(enrollmentDate);
        }
    }
}
