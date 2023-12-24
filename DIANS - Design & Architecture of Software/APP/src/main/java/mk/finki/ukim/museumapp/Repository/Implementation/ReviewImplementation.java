package mk.finki.ukim.museumapp.Repository.Implementation;

import mk.finki.ukim.museumapp.PipeAndFilter.Service.ReviewService;
import mk.finki.ukim.museumapp.PipeAndFilter.model.Review;
import mk.finki.ukim.museumapp.Repository.MuseumJPA;
import mk.finki.ukim.museumapp.Repository.ReviewJPA;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewImplementation implements ReviewService {
    public final ReviewJPA reviewJPA;
    public final MuseumJPA museumJPA;

    public ReviewImplementation(ReviewJPA reviewJPA, MuseumJPA museumJPA) {
        this.reviewJPA = reviewJPA;
        this.museumJPA = museumJPA;
    }

    @Override
    public List<Review> GetReviews(int MuseumId) {
        return reviewJPA.findReviewsByMuseumId(MuseumId);
    }

    @Override
    public List<Review> GetAllReviews() {
        return reviewJPA.findAllBy();
    }

    @Override
    public void deleteReview(int id) {
        reviewJPA.deleteById((long) id);
    }

    @Override
    public Review saveReview(String review, String username, int stars, int museum_id) {
        return reviewJPA.save(new Review(review, username, stars, museumJPA.findById(museum_id).get()));
    }
}
