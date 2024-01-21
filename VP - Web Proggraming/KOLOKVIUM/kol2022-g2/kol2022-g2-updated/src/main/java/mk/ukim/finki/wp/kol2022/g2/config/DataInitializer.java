package mk.ukim.finki.wp.kol2022.g2.config;


import mk.ukim.finki.wp.kol2022.g2.model.Course;
import mk.ukim.finki.wp.kol2022.g2.model.StudentType;
import mk.ukim.finki.wp.kol2022.g2.service.StudentService;
import mk.ukim.finki.wp.kol2022.g2.service.CourseService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class DataInitializer {

    private final CourseService courseService;

    private final StudentService service;

    public DataInitializer(CourseService courseService, StudentService service) {
        this.courseService = courseService;
        this.service = service;
    }

    private StudentType randomizeEventType(int i) {
        if (i % 3 == 0) return StudentType.MASTER;
        else if (i % 3 == 1) return StudentType.ADMIN;
        return StudentType.UNDERGRADUATE;
    }

    @PostConstruct
    public void initData() {
        for (int i = 1; i < 6; i++) {
            this.courseService.create("Course: " + i);
        }

        List<Course> courses = this.courseService.listAll();
        for (int i = 1; i < 11; i++) {
            this.service.create(
                    "Student: " + i,
                    "student." + i + "@wp.finki.ukim.mk",
                    "emp" + i,
                    this.randomizeEventType(i),
                    Stream.of(courses.get((i - 1) % 5).getId(), courses.get((i + 1) % 5).getId()).collect(Collectors.toList()),
                    LocalDate.now().minusYears((i + 1) / 2)
            );
        }
    }
}
