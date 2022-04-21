package PAP.Restaurantpol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import org.springframework.boot.test.context.SpringBootTest;

import PAP.Restaurantpol.model.Restaurant_instance;

@SpringBootTest
public class RestaurantpolRestaurant_instanceTests {
    UUID the_uuid = UUID.randomUUID();
    Restaurant_instance testRestaurant_instance = new Restaurant_instance(the_uuid, "TheBest Address", 2.5f, "TestSlug",
            "RestTempId");

    @Test
    void initTest_1() {
        UUID expected = the_uuid;
        UUID actual = testRestaurant_instance.getRestaurant_instance_id();
        assertEquals(expected, actual);
    }

    @Test
    void initTest_2() {
        String expected = "TheBest Address";
        String actual = testRestaurant_instance.getAddress_id();
        assertEquals(expected, actual);
    }

    @Test
    void initTest_4() {
        String expected = "TestSlug";
        String actual = testRestaurant_instance.getSlug();
        assertEquals(expected, actual);
    }

    @Test
    void initTest_5() {
        String expected = "RestTempId";
        String actual = testRestaurant_instance.getRestaurant_template_id();
        assertEquals(expected, actual);
    }

    @Test
    void setRestaurant_instance_idTest() {
        the_uuid = UUID.randomUUID();
        testRestaurant_instance.setRestaurant_instance_id(the_uuid);
        UUID actual = testRestaurant_instance.getRestaurant_instance_id();
        assertEquals(the_uuid, actual);
    }

    @Test
    void setAddress_idTest() {
        String expected = "New Better Address";
        testRestaurant_instance.setAddress_id(expected);
        String actual = testRestaurant_instance.getAddress_id();
        assertEquals(expected, actual);
    }

    @Test
    void setSlugTest() {
        String expected = "New Gut SLUG";
        testRestaurant_instance.setSlug(expected);
        String actual = testRestaurant_instance.getSlug();
        assertEquals(expected, actual);
    }

    @Test
    void setRestaurant_template_idTest() {
        String expected = "TemplateForTesting";
        testRestaurant_instance.setRestaurant_template_id(expected);
        String actual = testRestaurant_instance.getRestaurant_template_id();
        assertEquals(expected, actual);
    }

    @Test
    void toStringTest() {
        String expected = "ID: " + the_uuid + " TheBest Address 2.5 TestSlug RestTempId";
        String actual = testRestaurant_instance.toString();
        assertEquals(expected, actual);
    }
}
