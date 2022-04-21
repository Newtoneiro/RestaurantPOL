package PAP.Restaurantpol.dao;

import java.util.List;

import PAP.Restaurantpol.model.Restaurant;
import PAP.Restaurantpol.model.Restaurant_instance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantDAS {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // public Optional<String> addRestaurant(Restaurant restaurant) {
    //     String id = UUID.randomUUID().toString();

    //     String sql = """
    //     INSERT INTO restaurants
    //         (restaurant_id, name, cuisine_id, address_id, description, rating, slug, img_url)
    //     VALUES
    //         (?, ?, ?, ?, ?, ?, ?, ?)
    //     """;

    //     Optional<String> returnId;
    //     try {
    //         jdbcTemplate.update(sql, id, restaurant.getName(), restaurant.getCuisineId(), restaurant.getAddressId(),
    //         restaurant.getDescription(), restaurant.getRating(), restaurant.getSlug(), restaurant.getImgUrl());
    //         returnId = Optional.of(id);
    //         return returnId;
    //     } catch (Exception e) {
    //         System.out.println(e.getMessage());
    //         returnId = Optional.empty();
    //         return returnId;
    //     }
    // }
    
    public List<Restaurant> selectAllRestaurants() {
        String sql = """
        SELECT R.restaurant_id, RT.restaurant_name as name, RT.restaurant_description, RV.rating, R.slug, A.street, A.post_code, A.city, A.country, RT.img_url, A.latitude, A.longitude
        FROM
            restaurant_instance R
        INNER JOIN
            restaurant_template RT
        on
            R.restaurant_template_id = RT.restaurant_template_id
        INNER JOIN 
            addresses A
        ON 
            R.address_id = A.address_id
        JOIN
            restaurant_avg_rating RV
        on
            RV.restaurant_id = R.restaurant_id
        """;

        var rowMapper = BeanPropertyRowMapper.newInstance(Restaurant.class);

        var restaurants = jdbcTemplate.query(sql, rowMapper);

        return restaurants;
    }

    public List<Restaurant> selectRestaurants(String cuisine, int rating) {
        String sql = String.format("""
        SELECT
            R.restaurant_id,
            RT.restaurant_name,
            C.name as cuisine,
            RT.restaurant_description,
            RV.rating,
            R.slug,
            RT.img_url,
            A.longitude,
            A.latitude,
            A.street,
            A.post_code,
            A.city,
            A.country
        FROM
            restaurant_instance R
        INNER JOIN
            restaurant_template RT
        on
            R.restaurant_template_id = RT.restaurant_template_id
        INNER JOIN
            addresses A
        ON
            R.address_id = A.address_id
        INNER JOIN
            cuisines C
        ON
            RT.cuisine_id = C.cuisine_id
        JOIN
            restaurant_avg_rating RV
        on
            RV.restaurant_id = R.restaurant_id
        WHERE C.name LIKE '%s' AND RV.rating >= %d
        """, cuisine, rating, rating);
        
        if (rating != 0) {
            sql += String.format(" AND R.rating < %d", rating+1);
        }

        var rowMapper = BeanPropertyRowMapper.newInstance(Restaurant.class);

        var restaurants = jdbcTemplate.query(sql, rowMapper);

        return restaurants;
    }

    public Restaurant selectRestaurant(String slug) {
        String sql = String.format("""
        SELECT
            R.restaurant_id,
            RT.restaurant_name as name,
            C.name as cuisine,
            RT.restaurant_description as description,
            RV.rating,
            R.slug,
            RT.img_url,
            A.longitude,
            A.latitude,
            A.street,
            A.post_code,
            A.city,
            A.country
        FROM
            restaurant_instance R
        INNER JOIN
            restaurant_template RT
        on
            R.restaurant_template_id = RT.restaurant_template_id
        INNER JOIN
            addresses A
        ON
            R.address_id = A.address_id
        INNER JOIN
            cuisines C
        ON
            RT.cuisine_id = C.cuisine_id
        JOIN
            restaurant_avg_rating RV
        on
            RV.restaurant_id = R.restaurant_id
        WHERE R.slug = '%s'
        """, slug);

        try{
            var rowMapper = BeanPropertyRowMapper.newInstance(Restaurant.class);
            var restaurant = jdbcTemplate.queryForObject(sql, rowMapper);
            return restaurant;
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<String> getTemplates(){
        String sql = String.format("""
        SELECT
            restaurant_name
        FROM
            restaurant_template
        """);

        List<String> data = jdbcTemplate.queryForList(sql, String.class);
        return data;
    }

    public String getTemplateId(String name){
        String sql = String.format("""
        SELECT
            restaurant_template_id
        FROM
            restaurant_template
        WHERE
            restaurant_name = '%s'
        """, name);

        String output = jdbcTemplate.queryForObject(sql, String.class);
        return output;
    }

    public boolean addRestaurantInstance(Restaurant_instance r_i){
        String sql = String.format("""
        INSERT INTO restaurant_instance (restaurant_id, address_id, slug, restaurant_template_id)
        values ('%s', '%s', '%s', '%s')
        """, r_i.getRestaurant_instance_id(), r_i.getAddress_id(), r_i.getSlug().replace("\'", "\'\'"), r_i.getRestaurant_template_id());

        try {
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean removeRestaurant(String id){
        String sql = String.format("""
        DELETE FROM restaurant_instance 
        WHERE restaurant_id = '%s'
        """, id);
        try {
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
