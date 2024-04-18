package mk.finki.ukim.museumapp.Repository;

import mk.finki.ukim.museumapp.PipeAndFilter.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewJPA extends JpaRepository<Review, Long> {

    List<Review> findReviewsByMuseumId(int MuseumId);

    List<Review> findAllBy();

    void deleteById(Long id);
}
