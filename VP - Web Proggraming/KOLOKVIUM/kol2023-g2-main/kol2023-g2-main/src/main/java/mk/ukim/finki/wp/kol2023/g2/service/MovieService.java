package mk.ukim.finki.wp.kol2023.g2.service;

import mk.ukim.finki.wp.kol2023.g2.model.Movie;
import mk.ukim.finki.wp.kol2023.g2.model.Genre;
import mk.ukim.finki.wp.kol2023.g2.model.exceptions.InvalidMovieIdException;
import mk.ukim.finki.wp.kol2023.g2.model.exceptions.InvalidDirectorIdException;

import java.util.List;

public interface MovieService {

    /**
     * @return List of all movies in the database
     */
    List<Movie> listAllMovies();

    /**
     * returns the movie with the given id
     *
     * @param id The id of the movie that we want to obtain
     * @return
     * @throws InvalidMovieIdException when there is no movie with the given id
     */
    Movie findById(Long id);

    /**
     * This method is used to create a new movie, and save it in the database.
     *
     * @param name
     * @param description
     * @param rating
     * @param genre
     * @param director
     * @return The movie that is created. The id should be generated when the movie is created.
     * @throws InvalidDirectorIdException when there is no director with the given id
     */
    Movie create(String name, String description, Double rating, Genre genre, Long director);

    /**
     * This method is used to update a movie, and save it in the database.
     *
     * @param id The id of the movie that is being edited
     * @param name
     * @param description
     * @param rating
     * @param genre
     * @param director
     * @return The movie that is updated.
     * @throws InvalidMovieIdException when there is no movie with the given id
     * @throws InvalidDirectorIdException when there is no director with the given id
     */
    Movie update(Long id, String name, String description, Double rating, Genre genre, Long director);

    /**
     * Method that should delete a movie. If the id is invalid, it should throw InvalidMovieIdException.
     *
     * @param id
     * @return The movie that is deleted.
     * @throws InvalidMovieIdException when there is no movie with the given id
     */
    Movie delete(Long id);

    /**
     * Method that should vote for a movie. If the id is invalid, it should throw InvalidMovieIdException.
     *
     * @param id
     * @return The movie that is voted for.
     * @throws InvalidMovieIdException when there is no movie with the given id
     */
    Movie vote(Long id);

    /**
     * The implementation of this method should use repository implementation for the filtering.
     *
     * @param rating          Double that is used to filter the movies that have less rating than this value.
     *                        This param can be null, and is not used for filtering in this case.
     * @param genre           Used for filtering the movies that are from this genre.
     *                        This param can be null, and is not used for filtering in this case.
     * @return The movies that meet the filtering criteria
     */
    List<Movie> listMoviesWithRatingLessThenAndGenre(Double rating, Genre genre);
}
