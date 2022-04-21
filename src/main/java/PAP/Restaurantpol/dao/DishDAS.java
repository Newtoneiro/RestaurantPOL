package PAP.Restaurantpol.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import PAP.Restaurantpol.model.Dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DishDAS {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public List<Dish> selectAllDishes(String restaurant_id) {
        String sql = String.format("""
        SELECT D.dish_id, D.name, D.price, D.description, D.img_url
        FROM dishes D
        where restaurant_id = '%s'
        """, restaurant_id);
        try{
            var rowMapper = BeanPropertyRowMapper.newInstance(Dish.class);
            var dishes = jdbcTemplate.query(sql, rowMapper);
            return dishes;
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Optional<String> insertDish(Dish dish) {
        String id = UUID.randomUUID().toString();

        String sql = String.format("""
        INSERT INTO dishes (dish_id, name, price, description, img_url, restaurant_id)
        VALUES ('%s', '%s', %s, '%s', '%s', '%s')
        """, id, dish.getName().replace("\'", "\'\'"), dish.getPrice(), dish.getDescription().replace("\'", "\'\'"), dish.getImgUrl(), dish.getRestaurantId());

        Optional<String> returnId;
        try {
            jdbcTemplate.execute(sql);
            returnId = Optional.of(id);
            return returnId;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            returnId = Optional.empty();
            return returnId;
        }
    }

    public Optional<String> removeDish(String id){
        String sql = String.format("""
        DELETE FROM dishes WHERE dish_id = '%s'
        """, id);

        Optional<String> returnId;
        try {
            jdbcTemplate.execute(sql);
            returnId = Optional.of(id);
            return returnId;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            returnId = Optional.empty();
            return returnId;
        }
    }
}
