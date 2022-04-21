package PAP.Restaurantpol.model;

import PAP.Restaurantpol.utils.UserType;

public class User {

    private String userId;
    private String username;
    private String password;
    private String newPassword;
    private String firstName;
    private String lastName;
    private String email;
    private UserType admin;
    private String favouriteRestaurant;

    public User() {
    }

    public User(String userId, String username, String password, String newPassword, String firstName, String lastName, String email, String admin, String favouriteRestaurant) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.newPassword = newPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.admin = UserType.create(admin);
        this.favouriteRestaurant = favouriteRestaurant;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdmin(String admin) {
        this.admin = UserType.create(admin);
    }

    public void setFavouriteRestaurant(String favouriteRestaurant) {
        this.favouriteRestaurant = favouriteRestaurant;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public UserType getAdmin() {
        return admin;
    }

    public String getFavouriteRestaurant() {
        return favouriteRestaurant;
    }
    
    @Override
    public String toString() {
        return "ID: " + userId + " Username: " + username + " Name: " + firstName + " " + lastName;
    }
}
