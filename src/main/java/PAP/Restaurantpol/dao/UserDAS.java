package PAP.Restaurantpol.dao;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import PAP.Restaurantpol.model.User;
import com.google.common.hash.Hashing;

@Repository
public class UserDAS {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean insertUser(User user) {
        String hashedPassword = Hashing.sha256()
            .hashString(user.getPassword(), StandardCharsets.UTF_8)
            .toString();

        String id = UUID.randomUUID().toString();
        
        String sql = String.format("""
        INSERT INTO users (user_id, first_name, last_name, email, username, password)
        VALUES ('%s', '%s', '%s', '%s', '%s', '%s')
        """,
        id, user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername(), hashedPassword);
        try {
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<User> selectAllUsers() {
        String sql = "SELECT * FROM users";

        var rowMapper = BeanPropertyRowMapper.newInstance(User.class);

        var users = jdbcTemplate.query(sql, rowMapper);

        return users;
    }

    public User selectUserByField(String fieldName, String value) {
        String sql = String.format("SELECT * FROM users WHERE %s = '%s'", fieldName, value);

        var rowMapper = BeanPropertyRowMapper.newInstance(User.class);

        var user = jdbcTemplate.queryForObject(sql, rowMapper);
        
        return user;
    }

    public Optional<User> getUserInfo(String userId) {
        String sql = String.format("""
        SELECT
            user_id,
            first_name,
            last_name,
            email,
            username,
            favourite_restaurant
        FROM users
        WHERE user_id = '%s'
        """, userId);

        var rowMapper = BeanPropertyRowMapper.newInstance(User.class);

        try {
            var user = jdbcTemplate.queryForObject(sql, rowMapper);
            return Optional.of(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    public Boolean deleteUserById(String userId) {        
        String sql = String.format("""
        DELETE FROM users
        WHERE user_id = '%s'
        """, userId);
        try {
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Optional<Boolean> updateUserById(User user, Boolean ifUpdatePassword) {
        String sql = String.format("""
        UPDATE users
        SET first_name = '%s', last_name = '%s', email = '%s', username = '%s', favourite_restaurant = '%s'
        """, user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername(), user.getFavouriteRestaurant());
        
        if (ifUpdatePassword) {
            String hashedPassword = Hashing.sha256()
                .hashString(user.getNewPassword(), StandardCharsets.UTF_8)
                .toString();
            sql += String.format(", password = '%s'", hashedPassword);
        }

        sql += String.format("WHERE user_id = '%s'", user.getUserId());

        try {
            jdbcTemplate.execute(sql);
            return Optional.of(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    public User checkUserCredentials(User user) {        
        String hashedPassword = Hashing.sha256()
            .hashString(user.getPassword(), StandardCharsets.UTF_8)
            .toString();
        
        String sql = String.format("""
        SELECT user_id, first_name, admin FROM users WHERE username = '%s' AND password = '%s'
        """, user.getUsername(), hashedPassword);
        
        var rowMapper = BeanPropertyRowMapper.newInstance(User.class);
        return jdbcTemplate.queryForObject(sql, rowMapper);
    }

    public Boolean checkUserPassword(String userId, String password) {        
        String hashedPassword = Hashing.sha256()
            .hashString(password, StandardCharsets.UTF_8)
            .toString();
        
        String sql = String.format("""
        SELECT COUNT(*)
        FROM dual
        WHERE EXISTS (SELECT user_id FROM users WHERE user_id = '%s' AND password = '%s')
        """, userId, hashedPassword);
        
        var result = jdbcTemplate.queryForObject(sql, Integer.class);

        return result == 1;
    }

    public Map<String, Object> checkUsernameEmailAvailability(String username, String email) {
        String sql = String.format("""
        SELECT 
            (
                SELECT COUNT(*)
                FROM dual
                WHERE EXISTS (SELECT user_id FROM users WHERE username = '%s')
            ) as username,
            (
                SELECT COUNT(*)
                FROM dual
                WHERE EXISTS (SELECT user_id FROM users WHERE email = '%s')
            ) as email
        FROM dual
        """, username, email);

        final Map<String, Object> map = jdbcTemplate.queryForMap(sql);

        return map;
    }

}
