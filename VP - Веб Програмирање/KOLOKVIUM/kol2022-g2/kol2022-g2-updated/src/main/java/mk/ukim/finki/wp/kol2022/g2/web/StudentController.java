package mk.ukim.finki.wp.kol2022.g2.web;

import mk.ukim.finki.wp.kol2022.g2.model.Student;
import mk.ukim.finki.wp.kol2022.g2.model.StudentType;
import mk.ukim.finki.wp.kol2022.g2.service.StudentService;
import mk.ukim.finki.wp.kol2022.g2.service.CourseService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class StudentController {

    private final StudentService service;
    private final CourseService courseService;

    public StudentController(StudentService service, CourseService courseService) {
        this.service = service;
        this.courseService = courseService;
    }

    /**
     * This method should use the "list.html" template to display all entities.
     * The method should be mapped on paths '/' and '/students'.
     * The arguments that this method takes are optional and can be 'null'.
     *
     * @return The view "list.html".
     */
    @GetMapping({"/", "/students"})
    public String showList(Long courseId, Integer yearsOfStudying, Model model) {
        List<Student> students;
        if (courseId == null && yearsOfStudying == null) {
            students = this.service.listAll();
        } else {
            students = this.service.filter(courseId, yearsOfStudying);
        }
        model.addAttribute("students", students);
        model.addAttribute("courses", this.courseService.listAll());
        model.addAttribute("type", StudentType.values());
        return "list";
    }

    /**
     * This method should display the "form.html" template.
     * The method should be mapped on path '/students/add'.
     *
     * @return The view "form.html".
     */
    @GetMapping("/students/add")
    public String showAdd(Model model) {
        model.addAttribute("courses", this.courseService.listAll());
        model.addAttribute("type", StudentType.values());
        return "form";
    }

    /**
     * This method should display the "form.html" template.
     * However, in this case all 'input' elements should be filled with the appropriate value for the entity that is updated.
     * The method should be mapped on path '/students/[id]/edit'.
     *
     * @return The view "form.html".
     */
    @GetMapping("/students/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        model.addAttribute("student", this.service.findById(id));
        model.addAttribute("courses", this.courseService.listAll());
        model.addAttribute("type", StudentType.values());
        return "form";
    }

    /**
     * This method should create an entity given the arguments it takes.
     * The method should be mapped on path '/students'.
     * After the entity is created, the list of entities should be displayed.
     *
     * @return The view "list.html".
     */
    @PostMapping("/students")
    public String create(@RequestParam String name,
                         @RequestParam String email,
                         @RequestParam String password,
                         @RequestParam StudentType type,
                         @RequestParam List<Long> coursesId,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate enrollmentDate) {
        this.service.create(name, email, password, type, coursesId, enrollmentDate);
        return "redirect:/";
    }

    /**
     * This method should update an entity given the arguments it takes.
     * The method should be mapped on path '/students/[id]'.
     * After the entity is updated, the list of entities should be displayed.
     *
     * @return The view "list.html".
     */
    @PostMapping("/students/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam String email,
                         @RequestParam String password,
                         @RequestParam StudentType type,
                         @RequestParam List<Long> coursesId,
                         @RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate enrollmentDate) {
        this.service.update(id, name, email, password, type, coursesId, enrollmentDate);
        return "redirect:/students";
    }

    /**
     * This method should delete the entities that has the appropriate identifier.
     * The method should be mapped on path '/students/[id]/delete'.
     * After the entity is deleted, the list of entities should be displayed.
     *
     * @return The view "list.html".
     */
    @PostMapping("/students/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.service.delete(id);
        return "redirect:/students";
    }
}
