package PAP.Restaurantpol.dao;

import java.util.List;

import PAP.Restaurantpol.model.Cuisine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CuisineDAS {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public List<Cuisine> selectAllCuisines() {
        String sql = """
        SELECT *
        FROM cuisines
        """;

        var rowMapper = BeanPropertyRowMapper.newInstance(Cuisine.class);

        var cuisines = jdbcTemplate.query(sql, rowMapper);

        return cuisines;
    }
}
