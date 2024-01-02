package mk.ukim.finki.wp.kol2022.g3.model;

import java.time.LocalDate;
import java.util.List;

public class ForumUser {

    public ForumUser() {
    }

    public ForumUser(String name, String email, String password, ForumUserType type, List<Interest> interests, LocalDate birthday) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
        this.interests = interests;
        this.birthday = birthday;
    }

    private Long id;

    private LocalDate birthday;

    private String name;

    private String email;

    private String password;

    private ForumUserType type;

    private List<Interest> interests;

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

    public ForumUserType getType() {
        return type;
    }

    public void setType(ForumUserType type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Interest> getInterests() {
        return interests;
    }

    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
