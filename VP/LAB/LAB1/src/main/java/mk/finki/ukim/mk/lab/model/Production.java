package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Entity
@Table(name = "production")
@Getter
public class Production {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;
    public String country;
    public String address;

    public Production(String name, String country, String address) {
        this.name = name;
        this.country = country;
        this.address = address;
    }

    public Production() {

    }

    public String getName() {
        return name;
    }
}
