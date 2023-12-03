package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data //(automatic getter/setter annotation
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String password;
    private LocalDate dateOfBirth;

    private List <ShoppingCart> carts;

    public User(String username, String name, String surname, String password, LocalDate dateOfBirth, List<ShoppingCart> carts) {
        this.id = (long) (Math.random() * 1000);
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.carts = carts;
    }


    public User() {

    }
}
