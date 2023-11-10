package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.management.ConstructorParameters;

@Data //(automatic getter/setter annotation
@AllArgsConstructor //automatic constructor
public class Movie {
    public String title;
    public String summary;
    public double rating;
}
