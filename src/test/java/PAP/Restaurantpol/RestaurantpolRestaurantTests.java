package PAP.Restaurantpol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import org.springframework.boot.test.context.SpringBootTest;

import PAP.Restaurantpol.model.Restaurant;

import PAP.Restaurantpol.exceptions.IncorrectTestException;
import PAP.Restaurantpol.exceptions.FloatRatingException;

@SpringBootTest
public class RestaurantpolRestaurantTests {

    Restaurant testRestaurant = new Restaurant();

    @Test
    void setRestaurantIdTest() {
        UUID expected = UUID.randomUUID();
        testRestaurant.setRestaurantId(expected);
        UUID actual = testRestaurant.getRestaurantId();
        assertEquals(expected, actual);
    }

    @Test
    void setNameTest() {
        String expected = "TestNameeee";
        testRestaurant.setName(expected);
        String actual = testRestaurant.getName();
        assertEquals(expected, actual);
    }

    @Test
    void setStreetTest() {
        String expected = "TestSteetName";
        testRestaurant.setStreet(expected);
        String actual = testRestaurant.getStreet();
        assertEquals(expected, actual);
    }

    @Test
    void setPostCodeTest() {
        String expected = "53449";
        testRestaurant.setPostCode(expected);
        String actual = testRestaurant.getPostCode();
        assertEquals(expected, actual);
    }

    @Test
    void setCityTest() {
        String expected = "Dublin";
        testRestaurant.setCity(expected);
        String actual = testRestaurant.getCity();
        assertEquals(expected, actual);
    }

    @Test
    void setCountryTest() {
        String expected = "Canada";
        testRestaurant.setCountry(expected);
        String actual = testRestaurant.getCountry();
        assertEquals(expected, actual);
    }

    @Test
    void setRatingTest_1() throws FloatRatingException {
        float expected = 2.1f;
        testRestaurant.setRating(expected);
        float actual = testRestaurant.getRating();
        assertEquals(expected, actual);
    }

    @Test
    void setRatingTest_2() throws IncorrectTestException {
        try {
            testRestaurant.setRating(-253.51f);
            throw new IncorrectTestException("setRatingTest_2");
        } catch (FloatRatingException error) {
            String expected = "Rating is a values between 0.0 and 5.0, there is -253.51";
            String actual = error.getMessage();
            assertEquals(expected, actual);
        }
    }

    @Test
    void setRatingTest_3() throws IncorrectTestException {
        try {
            testRestaurant.setRating(593.4892f);
            throw new IncorrectTestException("setRatingTest_3");
        } catch (FloatRatingException error) {
            String expected = "Rating is a values between 0.0 and 5.0, there is 593.4892";
            String actual = error.getMessage();
            assertEquals(expected, actual);
        }
    }

    @Test
    void setRatingTest_4() throws FloatRatingException {
        float expected = 0.0f;
        testRestaurant.setRating(expected);
        float actual = testRestaurant.getRating();
        assertEquals(expected, actual);
    }

    @Test
    void setRatingTest_5() throws FloatRatingException {
        float expected = 5.0f;
        testRestaurant.setRating(expected);
        float actual = testRestaurant.getRating();
        assertEquals(expected, actual);
    }
}
