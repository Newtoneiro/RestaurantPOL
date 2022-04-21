package PAP.Restaurantpol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import PAP.Restaurantpol.model.User;
import PAP.Restaurantpol.utils.UserType;

@SpringBootTest
class RestaurantpolUserTests {

	User testUser = new User("007", "testName", "testPassword", "testNewPassword", "testFirstName", "testLastName",
			"testEmail", "Y", "testFavRes");

	@Test
	void initTest_1() {
		String expected = "007";
		String actual = testUser.getUserId();
		assertEquals(expected, actual);
	}

	@Test
	void initTest_2() {
		String expected = "testName";
		String actual = testUser.getUsername();
		assertEquals(expected, actual);
	}

	@Test
	void initTest_3() {
		String expected = "testPassword";
		String actual = testUser.getPassword();
		assertEquals(expected, actual);
	}

	@Test
	void initTest_4() {
		String expected = "testNewPassword";
		String actual = testUser.getNewPassword();
		assertEquals(expected, actual);
	}

	@Test
	void initTest_5() {
		String expected = "testFirstName";
		String actual = testUser.getFirstName();
		assertEquals(expected, actual);
	}

	@Test
	void initTest_6() {
		String expected = "testLastName";
		String actual = testUser.getLastName();
		assertEquals(expected, actual);
	}

	@Test
	void initTest_7() {
		String expected = "testEmail";
		String actual = testUser.getEmail();
		assertEquals(expected, actual);
	}

	@Test
	void initTest_8() {
		UserType expected = UserType.ADMIN;
		UserType actual = testUser.getAdmin();
		assertEquals(expected, actual);
	}

	@Test
	void initTest_9() {
		String expected = "testFavRes";
		String actual = testUser.getFavouriteRestaurant();
		assertEquals(expected, actual);
	}

	@Test
	void setUserIdTest() {
		testUser.setUserId("024");
		String expected = "024";
		String actual = testUser.getUserId();
		assertEquals(expected, actual);
	}

	@Test
	void setUsernameTest() {
		testUser.setUsername("NikolaTesla");
		String expected = "NikolaTesla";
		String actual = testUser.getUsername();
		assertEquals(expected, actual);
	}

	@Test
	void setNewPasswordTest() {
		testUser.setNewPassword("$eCre+P@$Sw0rD");
		String expected = "$eCre+P@$Sw0rD";
		String actual = testUser.getNewPassword();
		assertEquals(expected, actual);
	}

	@Test
	void setFirstNameTest() {
		testUser.setFirstName("Nikola");
		String expected = "Nikola";
		String actual = testUser.getFirstName();
		assertEquals(expected, actual);
	}

	@Test
	void setLastNameTest() {
		testUser.setLastName("Tesla");
		String expected = "Tesla";
		String actual = testUser.getLastName();
		assertEquals(expected, actual);
	}

	@Test
	void setEmailTest() {
		testUser.setEmail("my@super.email");
		String expected = "my@super.email";
		String actual = testUser.getEmail();
		assertEquals(expected, actual);
	}

	@Test
	void setAdminTest() {
		testUser.setAdmin("N");
		UserType expected = UserType.USER;
		UserType actual = testUser.getAdmin();
		assertEquals(expected, actual);
	}

	@Test
	void setFavouriteRestaurantTest() {
		testUser.setFavouriteRestaurant("NotFastNotFood");
		String expected = "NotFastNotFood";
		String actual = testUser.getFavouriteRestaurant();
		assertEquals(expected, actual);
	}

	@Test
	void toStringTest() {
		User stringTestUser = new User("007", "testName", "testPassword", "testNewPassword", "testFirstName",
				"testLastName",
				"testEmail", "Y", "testFavRes");

		String expected = "ID: 007 Username: testName Name: testFirstName testLastName";
		String actual = stringTestUser.toString();
		assertEquals(expected, actual);
	}
}
