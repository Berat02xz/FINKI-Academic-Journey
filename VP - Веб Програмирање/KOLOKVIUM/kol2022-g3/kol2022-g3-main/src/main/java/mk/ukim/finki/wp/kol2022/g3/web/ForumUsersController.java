package mk.ukim.finki.wp.kol2022.g3.web;

import mk.ukim.finki.wp.kol2022.g3.model.ForumUser;
import mk.ukim.finki.wp.kol2022.g3.model.ForumUserType;
import mk.ukim.finki.wp.kol2022.g3.service.ForumUserService;
import mk.ukim.finki.wp.kol2022.g3.service.InterestService;

import java.time.LocalDate;
import java.util.List;

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
    public String showList(Long interestId, Integer age) {
        List<ForumUser> forumUsers;
        if (interestId == null && age == null) {
            forumUsers = this.service.listAll();
        } else {
            forumUsers = this.service.filter(interestId, age);
        }
        return "";
    }

    /**
     * This method should display the "form.html" template.
     * The method should be mapped on path '/users/add'.
     *
     * @return The view "form.html".
     */
    public String showAdd() {
        return "";
    }

    /**
     * This method should display the "form.html" template.
     * However, in this case all 'input' elements should be filled with the appropriate value for the entity that is updated.
     * The method should be mapped on path '/users/[id]/edit'.
     *
     * @return The view "form.html".
     */
    public String showEdit(Long id) {
        return "";
    }

    /**
     * This method should create an entity given the arguments it takes.
     * The method should be mapped on path '/users'.
     * After the entity is created, the list of entities should be displayed.
     *
     * @return The view "list.html".
     */
    public String create(String name,
                         String email,
                         String password,
                         ForumUserType type,
                         List<Long> interestId,
                         LocalDate birthday) {
        this.service.create(name, email, password, type, interestId, birthday);
        return "";
    }

    /**
     * This method should update an entity given the arguments it takes.
     * The method should be mapped on path '/users/[id]'.
     * After the entity is updated, the list of entities should be displayed.
     *
     * @return The view "list.html".
     */
    public String update(Long id,
                         String name,
                         String email,
                         String password,
                         ForumUserType type,
                         List<Long> interestId,
                         LocalDate birthday) {
        this.service.update(id, name, email, password, type, interestId, birthday);
        return "";
    }

    /**
     * This method should delete the entities that has the appropriate identifier.
     * The method should be mapped on path '/users/[id]/delete'.
     * After the entity is deleted, the list of entities should be displayed.
     *
     * @return The view "list.html".
     */
    public String delete(Long id) {
        this.service.delete(id);
        return "";
    }
}
