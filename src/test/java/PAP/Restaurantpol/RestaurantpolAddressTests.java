package PAP.Restaurantpol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import PAP.Restaurantpol.model.Address;

import java.util.UUID;

@SpringBootTest
public class RestaurantpolAddressTests {
    UUID test_uuid = UUID.randomUUID();
    Address testAddress = new Address(test_uuid, "testStreet", "02-424", "DiamontCity", "Boston", 53.32f, 44.2f);

    @Test
    void initTest_1() {
        UUID expected = test_uuid;
        UUID actual = testAddress.getAddress_id();
        assertEquals(expected, actual);
    }

    @Test
    void initTest_2() {
        String expected = "testStreet";
        String actual = testAddress.getStreet();
        assertEquals(expected, actual);
    }

    @Test
    void initTest_3() {
        String expected = "02-424";
        String actual = testAddress.getPost_code();
        assertEquals(expected, actual);
    }

    @Test
    void initTest_4() {
        String expected = "DiamontCity";
        String actual = testAddress.getCity();
        assertEquals(expected, actual);
    }

    @Test
    void initTest_5() {
        String expected = "Boston";
        String actual = testAddress.getCountry();
        assertEquals(expected, actual);
    }

    @Test
    void initTest_6() {
        float expected = 53.32f;
        float actual = testAddress.getLatitude();
        assertEquals(expected, actual);
    }

    @Test
    void initTest_7() {
        float expected = 44.2f;
        float actual = testAddress.getLongitude();
        assertEquals(expected, actual);
    }

    @Test
    void setAddress_idTest() {
        UUID new_uuid = UUID.randomUUID();
        testAddress.setAddress_id(new_uuid);
        UUID expected = new_uuid;
        UUID actual = testAddress.getAddress_id();
        assertEquals(expected, actual);
    }

    @Test
    void setStreetTest() {
        testAddress.setStreet("VeryLong");
        String expected = "VeryLong";
        String actual = testAddress.getStreet();
        assertEquals(expected, actual);
    }

    @Test
    void setPost_codeTest() {
        testAddress.setPost_code("00-909");
        String expected = "00-909";
        String actual = testAddress.getPost_code();
        assertEquals(expected, actual);
    }

    @Test
    void setCityTest() {
        testAddress.setCity("MorzeBlasku");
        String expected = "MorzeBlasku";
        String actual = testAddress.getCity();
        assertEquals(expected, actual);
    }

    @Test
    void setCountryTest() {
        testAddress.setCountry("CountryRoads");
        String expected = "CountryRoads";
        String actual = testAddress.getCountry();
        assertEquals(expected, actual);
    }

    @Test
    void setLatTest() {
        testAddress.setLatitude(90.09f);
        float expected = 90.09f;
        float actual = testAddress.getLatitude();
        assertEquals(expected, actual);
    }

    @Test
    void setLongTest() {
        testAddress.setLongitude(70.19f);
        float expected = 70.19f;
        float actual = testAddress.getLongitude();
        assertEquals(expected, actual);
    }

    @Test
    void toStringTest() {
        UUID new_uuid = UUID.randomUUID();
        testAddress.setAddress_id(new_uuid);
        String expected = "ID: " + new_uuid
                + " Street: testStreet Post code: 02-424 City: DiamontCity country: Boston longitude: 44.2 latitude 53.32";
        String actual = testAddress.toString();
        assertEquals(expected, actual);
    }
}
