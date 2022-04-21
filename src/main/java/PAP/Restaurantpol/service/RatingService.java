package PAP.Restaurantpol.service;

import PAP.Restaurantpol.dao.RatingDAS;
import PAP.Restaurantpol.model.Rating;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    private final RatingDAS ratingDao;

    @Autowired
    public RatingService(RatingDAS ratingDao) {
        this.ratingDao = ratingDao;
    }

    public double getAvgRating(String restaurantId) {
        return this.ratingDao.getAvgRating(restaurantId);
    }

    public boolean addRating(Rating rating) {
        return ratingDao.addRating(rating);
    }

    public List<Rating> getRatings(String restaurantId){
        return ratingDao.getRatings(restaurantId);
    }

    public boolean removeRating(String ratingId){
        return ratingDao.removeRating(ratingId);
    }
}
