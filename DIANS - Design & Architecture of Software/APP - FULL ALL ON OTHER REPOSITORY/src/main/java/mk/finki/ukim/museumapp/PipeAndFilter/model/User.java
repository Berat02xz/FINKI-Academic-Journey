package mk.finki.ukim.museumapp.PipeAndFilter.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;

    public User() {
    }

    public User(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role=role;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email=email;
        this.role="User";
    }
}
