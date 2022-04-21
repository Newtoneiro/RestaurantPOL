package PAP.Restaurantpol;

import PAP.Restaurantpol.service.RestaurantService;
import PAP.Restaurantpol.dao.RestaurantDAS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import PAP.Restaurantpol.dao.AddressDAS;

public class RestaurantpolToSlugTests {
    RestaurantDAS my_res_das = new RestaurantDAS();
    AddressDAS my_addres_das = new AddressDAS();
    RestaurantService my_res = new RestaurantService(my_res_das, my_addres_das);

    @Test
    void toSlug_1() {
        String expected = "test-taki-o";
        String actual = my_res.toSlug("test taki o");
        assertEquals(expected, actual);
    }

    @Test
    void toSlug_2() {
        String expected = "";
        String actual = my_res.toSlug("");
        assertEquals(expected, actual);
    }

    @Test
    void toSlug_3() {
        String expected = "-";
        String actual = my_res.toSlug("   ");
        assertEquals(expected, actual);
    }

    @Test
    void toSlug_4() {
        String expected = "spacja-na-koncu";
        String actual = my_res.toSlug("spacja na koncu ");
        assertEquals(expected, actual);
    }

    @Test
    void toSlug_5() {
        String expected = "spacja-na-poczatku";
        String actual = my_res.toSlug(" spacja na poczatku");
        assertEquals(expected, actual);
    }

    @Test
    void toSlug_6() {
        String expected = "duzo---w---srodku";
        String actual = my_res.toSlug("duzo   w   srodku");
        assertEquals(expected, actual);
    }
}
