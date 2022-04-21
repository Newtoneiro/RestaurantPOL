package PAP.Restaurantpol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import PAP.Restaurantpol.exceptions.IncorrectTestException;
import PAP.Restaurantpol.exceptions.IntRatingException;

import PAP.Restaurantpol.model.Review;

@SpringBootTest
public class RestaurantpolReviewTests {
    Review testReview;

    @Autowired
    public RestaurantpolReviewTests() throws IntRatingException {
        this.testReview = new Review("535", "TestUserName", 5, "Ideolo Place");
    }

    @Test
    void initTest_1() {
        String expected = "535";
        String actual = testReview.getId();
        assertEquals(expected, actual);
    }

    @Test
    void initTest_2() {
        String expected = "TestUserName";
        String actual = testReview.getusername();
        assertEquals(expected, actual);
    }

    @Test
    void initTest_3() {
        int expected = 5;
        int actual = testReview.getRating();
        assertEquals(expected, actual);
    }

    @Test
    void initTest_4() {
        String expected = "Ideolo Place";
        String actual = testReview.getDescript();
        assertEquals(expected, actual);
    }

    @Test
    void setReview_idTest() {
        testReview.setReview_id("639");
        String expected = "639";
        String actual = testReview.getId();
        assertEquals(expected, actual);
    }

    @Test
    void setUsernameTest() {
        testReview.setusername("TomekEdison");
        String expected = "TomekEdison";
        String actual = testReview.getusername();
        assertEquals(expected, actual);
    }

    @Test
    void setRatingTest() throws IntRatingException {
        testReview.setRating(2);
        int expected = 2;
        int actual = testReview.getRating();
        assertEquals(expected, actual);
    }

    @Test
    void setDescriptTest() {
        testReview.setDescript("Jednak nie ideolo");
        String expected = "Jednak nie ideolo";
        String actual = testReview.getDescript();
        assertEquals(expected, actual);
    }

    @Test
    void toStringTest() throws IntRatingException {
        Review testReview;
        testReview = new Review("535", "TestUserName", 5, "Ideolo Place");
        String expected = "ID: 535 username: TestUserNameRating: 5descript: Ideolo Place";
        String actual = testReview.toString();
        assertEquals(expected, actual);
    }

    @Test
    void setRatingExceptionTest_1() throws IncorrectTestException {
        try {
            testReview.setRating(10);
            throw new IncorrectTestException("setRatingExceptionTest_1");
        } catch (IntRatingException error) {
            String expected = "Rating is a values between 0 and 5. There is 10";
            assertEquals(expected, error.getMessage());
        }
    }

    @Test
    void setRatingExceptionTest_2() throws IncorrectTestException {
        try {
            testReview.setRating(-10);
            throw new IncorrectTestException("setRatingExceptionTest_2");
        } catch (IntRatingException error) {
            String expected = "Rating is a values between 0 and 5. There is -10";
            assertEquals(expected, error.getMessage());
        }
    }
}
