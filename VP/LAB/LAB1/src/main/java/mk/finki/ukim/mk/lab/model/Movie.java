package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data //(automatic getter/setter annotation
@AllArgsConstructor //automatic constructor
public class Movie {
    public String title;
    public String summary;
    public double rating;
    public Long id;
    public Production production;

    public Movie(String title, String summary, double rating, Production production) {
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.id = (long) (Math.random() * 1000);
        this.production = production;
    }

}
