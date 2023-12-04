package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data //(automatic getter/setter annotation
@AllArgsConstructor //automatic constructor
@Entity
@Table(name = "movies")
public class Movie {
    public String title;
    public String summary;
    public double rating;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    public Production production;

    public Movie(String title, String summary, double rating, Production production) {
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.id = (long) (Math.random() * 1000);
        this.production = production;
    }

    public Movie() {

    }
}
