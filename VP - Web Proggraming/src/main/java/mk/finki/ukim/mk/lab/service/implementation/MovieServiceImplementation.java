package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.repository.jpa.MovieRepositoryJPA;
import mk.finki.ukim.mk.lab.service.MovieService;
import mk.finki.ukim.mk.lab.service.ProductionInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImplementation implements MovieService {

    private final MovieRepositoryJPA movieRepository; //kreiranje na movierepo objekt
    private final ProductionInterface ProductionInterface;

    @Autowired
    public MovieServiceImplementation(MovieRepositoryJPA movieRepository, mk.finki.ukim.mk.lab.service.ProductionInterface productionInterface) {
        this.movieRepository = movieRepository;
        this.ProductionInterface = productionInterface;
    }

    @Override
    public List<Movie> listAll(){
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMovies(String text, double rating){
        return movieRepository.findMoviesByTitleContainingAndRatingGreaterThanEqual(text,rating);
    }

    @Override
    public Movie movieById(Long id){
        return this.movieRepository.findMovieById(id);
    }

    @Override
    public void delete(Long movieId) {
        movieRepository.deleteById(movieId);
    }

    @Override
    public void edit(Long movieId, String name, String summary, Double rating, Long id) {
        movieRepository.findMovieById(movieId).setTitle(name);
        movieRepository.findMovieById(movieId).setSummary(summary);
        movieRepository.findMovieById(movieId).setRating(rating);
        movieRepository.findMovieById(movieId).setProduction(ProductionInterface.findById(id));
    }

    @Override
    public void save(String name, String summary, Double rating, Long id) {
        this.movieRepository.save(new Movie(name,summary,rating, ProductionInterface.findById(id)));
    }


}
