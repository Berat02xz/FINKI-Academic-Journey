package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepositoryJPA extends JpaRepository<Movie,Long>{
    List<Movie> findMoviesByTitleLikeAndRatingGreaterThanEqual(String title, Double rating);

    Movie findMovieById(Long id);

    void deleteMovieById(Long id);

    //edit(movieId,name,summary,rating,id);


}
