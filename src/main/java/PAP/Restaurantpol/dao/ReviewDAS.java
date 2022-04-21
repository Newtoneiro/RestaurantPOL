package PAP.Restaurantpol.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import PAP.Restaurantpol.model.Review;

@Repository
public class ReviewDAS {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Review> selectAllReviews(){
        String sql = "SELECT review_id, username, rating, descript FROM REVIEWS";
        var rowMapper = BeanPropertyRowMapper.newInstance(Review.class);
        var reviews = jdbcTemplate.query(sql, rowMapper);
        return reviews;
    }

    public boolean insertReview(Review review) {
        String sql = String.format("""
        INSERT INTO Reviews (review_id, username, rating, descript)
        VALUES ('%s', '%s', '%s', '%s')
        """, review.getId(), review.getusername().replace("\'", "\'\'"), review.getRating(), review.getDescript().replace("\'", "\'\'"));
        jdbcTemplate.execute(sql);
        
        return true;
    }

    public float selectAverageStars(){
        String sql = "Select avg(rating) from Reviews";
        float avg = jdbcTemplate.queryForObject(sql, Float.class);
        return avg;
    }

    public boolean removeReview(String review_id){
        String sql = String.format("""
        DELETE FROM REVIEWS WHERE review_id = '%s'
        """, review_id);
        jdbcTemplate.execute(sql);
        
        return true;
    }
}
