package PAP.Restaurantpol.dao;

import PAP.Restaurantpol.model.Rating;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RatingDAS {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public double getAvgRating(String restaurantId) {
        String query = String.format("""
            SELECT AVG(rating)
            FROM ratings
            WHERE restaurant_id = '%s'
            """, restaurantId);

        var rowMapper = BeanPropertyRowMapper.newInstance(double.class);

        var avg_rating = jdbcTemplate.query(query, rowMapper);

        return avg_rating.get(0);
    }

    public boolean addRating(Rating rating){
        String sql = String.format("""
        INSERT INTO ratings (rating_id, restaurant_id, rating, descript, user_id)
        values ('%s', '%s', %s, '%s', '%s')
        """, rating.getRatingId(), rating.getRestaurantId(), rating.getRating(), rating.getDescript().replace("\'", "\'\'"), rating.getUserId());
        try {
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Rating> getRatings(String restaurantId){
        String sql = String.format("""
        SELECT r.rating_id as ratingId, r.restaurant_id as restaurantId, r.rating, r.descript, r.user_id as userId, u.username as username
        FROM ratings r
        JOIN users u
        on (r.user_id = u.user_id)
        where r.restaurant_id = '%s'
        """, restaurantId);

        var rowMapper = BeanPropertyRowMapper.newInstance(Rating.class);
        var ratings = jdbcTemplate.query(sql, rowMapper);
        return ratings;
    }

    public boolean removeRating(String ratingId){
        String sql = String.format("""
        DELETE FROM ratings WHERE rating_id = '%s'
        """, ratingId);
        try {
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
