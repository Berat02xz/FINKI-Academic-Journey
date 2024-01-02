package mk.ukim.finki.wp.kol2022.g3.web;

import mk.ukim.finki.wp.kol2022.g3.model.ForumUser;
import mk.ukim.finki.wp.kol2022.g3.model.ForumUserType;
import mk.ukim.finki.wp.kol2022.g3.service.ForumUserService;
import mk.ukim.finki.wp.kol2022.g3.service.InterestService;
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
public class ForumUsersController {

    private final ForumUserService service;
    private final InterestService skillService;

    public ForumUsersController(ForumUserService service, InterestService skillService) {
        this.service = service;
        this.skillService = skillService;
    }

    /**
     * This method should use the "list.html" template to display all entities.
     * The method should be mapped on paths '/' and '/users'.
     * The arguments that this method takes are optional and can be 'null'.
     *
     * @return The view "list.html".
     */
    @GetMapping({"/", "/users"})
    public String showList(Long interestId, Integer age, Model model) {
        List<ForumUser> forumUsers;
        if (interestId == null && age == null) {
            forumUsers = this.service.listAll();
        } else {
            forumUsers = this.service.filter(interestId, age);
        }
        model.addAttribute("forumUsers", forumUsers);
        model.addAttribute("interests", this.skillService.listAll());
        model.addAttribute("type", ForumUserType.values());
        return "list";
    }

    /**
     * This method should display the "form.html" template.
     * The method should be mapped on path '/users/add'.
     *
     * @return The view "form.html".
     */
    @GetMapping("/users/add")
    public String showAdd(Model model) {
        model.addAttribute("interests", this.skillService.listAll());
        model.addAttribute("type", ForumUserType.values());
        return "form";
    }

    /**
     * This method should display the "form.html" template.
     * However, in this case all 'input' elements should be filled with the appropriate value for the entity that is updated.
     * The method should be mapped on path '/users/[id]/edit'.
     *
     * @return The view "form.html".
     */
    @GetMapping("/users/{id}/edit")
    public String showEdit(@PathVariable Long id,Model model) {
        ForumUser forumUser = this.service.findById(id);
        model.addAttribute("forumUser", forumUser);
        model.addAttribute("interests", this.skillService.listAll());
        model.addAttribute("type", ForumUserType.values());
        return "form";
    }

    /**
     * This method should create an entity given the arguments it takes.
     * The method should be mapped on path '/users'.
     * After the entity is created, the list of entities should be displayed.
     *
     * @return The view "list.html".
     */
    @PostMapping("/users")
    public String create(@RequestParam String name,
                         @RequestParam String email,
                         @RequestParam String password,
                         @RequestParam ForumUserType type,
                         @RequestParam List<Long> interestId,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthday) {
        this.service.create(name, email, password, type, interestId, birthday);
        return "redirect:/users";
    }

    /**
     * This method should update an entity given the arguments it takes.
     * The method should be mapped on path '/users/[id]'.
     * After the entity is updated, the list of entities should be displayed.
     *
     * @return The view "list.html".
     */
    @PostMapping("/users/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam String email,
                         @RequestParam String password,
                         @RequestParam  ForumUserType type,
                         @RequestParam  List<Long> interestId,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthday) {
        this.service.update(id, name, email, password, type, interestId, birthday);
        return "redirect:/users";
    }

    /**
     * This method should delete the entities that has the appropriate identifier.
     * The method should be mapped on path '/users/[id]/delete'.
     * After the entity is deleted, the list of entities should be displayed.
     *
     * @return The view "list.html".
     */
    @PostMapping("/users/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.service.delete(id);
        return "redirect:/users";
    }
}
