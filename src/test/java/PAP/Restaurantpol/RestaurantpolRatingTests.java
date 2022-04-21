package PAP.Restaurantpol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import org.springframework.boot.test.context.SpringBootTest;

import PAP.Restaurantpol.model.Rating;

@SpringBootTest
public class RestaurantpolRatingTests {

    UUID rat_id = UUID.randomUUID();
    String res_id = "ResIdTest";
    String cli_id = "ClientTestIDNumberrrr";
    int ratg = 2;

    Rating testRating = new Rating();

    @Test
    void ratingTest() {
        int expected = ratg;
        testRating.setRating(expected);
        int actual = testRating.getRating();
        assertEquals(expected, actual);
    }

    @Test
    void RestaurantIdTest() {
        String expected = res_id;
        testRating.setRestaurantId(expected);
        String actual = testRating.getRestaurantId();
        assertEquals(expected, actual);
    }

    @Test
    void RatingIdTest() {
        UUID expected = rat_id;
        testRating.setRatingId(expected);
        UUID actual = testRating.getRatingId();
        assertEquals(expected, actual);
    }

    @Test
    void UserIdTest() {
        String expected = cli_id;
        testRating.setUserId(expected);
        String actual = testRating.getUserId();
        assertEquals(expected, actual);
    }

}
