package PAP.Restaurantpol.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import PAP.Restaurantpol.model.Address;

@Repository
public class AddressDAS {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean addAddress(Address address){
        String sql = String.format("""
        INSERT INTO addresses (address_id, street, post_code, city, country, latitude, longitude)
        VALUES ('%s', '%s', '%s', '%s', '%s', '%f', '%f')
        """, address.getAddress_id(), address.getStreet().replace("\'", "\'\'"), address.getPost_code().replace("\'", "\'\'"), address.getCity().replace("\'", "\'\'"), address.getCountry().replace("\'", "\'\'"), address.getLatitude(), address.getLongitude());
        try {
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
