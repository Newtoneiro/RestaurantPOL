package PAP.Restaurantpol.service;

import java.util.List;

import PAP.Restaurantpol.dao.ReviewDAS;
import PAP.Restaurantpol.model.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewDAS reviewDao;

    @Autowired
    public ReviewService(ReviewDAS reviewDao) {
        this.reviewDao = reviewDao;
    }

    public List<Review> getAllReviews() {
        return reviewDao.selectAllReviews();
    }

    public boolean addReview(Review review) {
        return reviewDao.insertReview(review);
    }

    public float getAverageStars(){
        return reviewDao.selectAverageStars();
    }

    public boolean removeReview(String review_id){
        return reviewDao.removeReview(review_id);
    }
}
