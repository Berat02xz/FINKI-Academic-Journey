package mk.ukim.finki.wp.kol2022.g2.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Student {

    public Student() {
    }

    public Student(String name, String email, String password, StudentType type, List<Course> courses, LocalDate enrollmentDate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
        this.courses = courses;
        this.enrollmentDate = enrollmentDate;
    }

    @Id
    @GeneratedValue
    private Long id;

    @DateTimeFormat
    private LocalDate enrollmentDate;

    private String name;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private StudentType type;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Course> courses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StudentType getType() {
        return type;
    }

    public void setType(StudentType type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
