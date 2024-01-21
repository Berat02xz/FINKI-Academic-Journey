package mk.ukim.finki.wp.kol2023.g2.service.impl;

import mk.ukim.finki.wp.kol2023.g2.model.Genre;
import mk.ukim.finki.wp.kol2023.g2.model.Movie;
import mk.ukim.finki.wp.kol2023.g2.model.exceptions.InvalidMovieIdException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements mk.ukim.finki.wp.kol2023.g2.service.MovieService{

    private final mk.ukim.finki.wp.kol2023.g2.repository.MovieRepository movieRepository;
    private final mk.ukim.finki.wp.kol2023.g2.repository.DirectorRepository directorRepository;

    public MovieServiceImpl(mk.ukim.finki.wp.kol2023.g2.repository.MovieRepository movieRepository, mk.ukim.finki.wp.kol2023.g2.repository.DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
    }

    @Override
    public List<Movie> listAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id).orElseThrow(InvalidMovieIdException::new);
    }

    @Override
    public Movie create(String name, String description, Double rating, Genre genre, Long director) {
        return movieRepository.save(new Movie(name, description, rating, genre, directorRepository.findById(director).orElseThrow(InvalidMovieIdException::new)));
    }

    @Override
    public Movie update(Long id, String name, String description, Double rating, Genre genre, Long director) {
        Movie movie = movieRepository.findById(id).orElseThrow(InvalidMovieIdException::new);
        movie.setName(name);
        movie.setDescription(description);
        movie.setRating(rating);
        movie.setGenre(genre);
        movie.setDirector(directorRepository.findById(director).orElseThrow(InvalidMovieIdException::new));
        return movieRepository.save(movie);
    }

    @Override
    public Movie delete(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(InvalidMovieIdException::new);
        movieRepository.delete(movie);
        return movie;
    }

    @Override
    public Movie vote(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(InvalidMovieIdException::new);
        movie.setVotes(movie.getVotes() + 1);
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> listMoviesWithRatingLessThenAndGenre(Double rating, Genre genre) {
        if(rating == null && genre == null)
            return movieRepository.findAll();
        else if(rating == null)
            return movieRepository.findMoviesByGenre(genre);
        else if(genre == null)
            return movieRepository.findMoviesByRatingLessThan(rating);
        else
            return movieRepository.findMoviesByRatingLessThanAndGenre(rating, genre);
    }
}
