package PAP.Restaurantpol.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import PAP.Restaurantpol.model.Review;
import PAP.Restaurantpol.service.ReviewService;

@RequestMapping("/api/v1/review")
@RestController
@CrossOrigin
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/all")
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @PostMapping("/add")
    public boolean addReview(@RequestBody Review review){
        return reviewService.addReview(review);
    }

    @GetMapping("/getAvgStars")
    public float getAverageStars(){
        return reviewService.getAverageStars();
    }

    @PostMapping("/remove")
    public boolean removeReview(@RequestBody Review review){
        String review_id = review.getId().toString();
        return reviewService.removeReview(review_id);
    }
}
