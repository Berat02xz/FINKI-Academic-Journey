package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> listAll();
    List<Movie> searchMovies(String text, double rating);

    Movie movieById(Long id);

    void delete(Long movieId);

    void edit(Long movieId, String name, String summary, Double rating, Long id);

    void save(String name, String summary, Double rating, Long id);
}
