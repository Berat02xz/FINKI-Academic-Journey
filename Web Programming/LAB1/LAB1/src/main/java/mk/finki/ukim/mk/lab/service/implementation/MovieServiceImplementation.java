package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.repository.MovieRepository;
import mk.finki.ukim.mk.lab.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImplementation implements MovieService {

    private final MovieRepository movieRepository; //kreiranje na movierepo objekt

    @Autowired
    public MovieServiceImplementation(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    //
    // implementiranje na servisite od interfaces i MovieRepo metodite
    //
    @Override
    public List<Movie> listAll(){
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMovies(String text, double rating){
        return movieRepository.searchMovies(text,rating);
    }

}
