package PAP.Restaurantpol.api;

import PAP.Restaurantpol.model.Rating;
import PAP.Restaurantpol.service.RatingService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/rating")
@RestController
@CrossOrigin
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/getAvg")
    public double getAvgRating(String restaurantId) {
        return this.ratingService.getAvgRating(restaurantId);
    }

    @PostMapping("/add")
    public boolean addRating(@RequestBody Rating rating){
        return ratingService.addRating(rating);
    }

    @GetMapping("/getRatings")
    public List<Rating> getRatings(String restaurantId){
        return ratingService.getRatings(restaurantId);
    }

    @DeleteMapping("/remove")
    public boolean removeRating(String ratingId){
        return ratingService.removeRating(ratingId);
    }
}
