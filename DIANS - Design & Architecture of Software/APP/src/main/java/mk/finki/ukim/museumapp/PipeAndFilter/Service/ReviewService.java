package mk.finki.ukim.museumapp.PipeAndFilter.Service;

import mk.finki.ukim.museumapp.PipeAndFilter.model.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    List<Review> GetReviews(int MuseumId);


    List<Review> GetAllReviews();

    void deleteReview(int id);

    Review saveReview(String review, String username, int stars, int id);
}
