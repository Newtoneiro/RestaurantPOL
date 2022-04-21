package PAP.Restaurantpol.model;

import PAP.Restaurantpol.exceptions.FloatRatingException;

import java.util.UUID;

public class Restaurant {
    private UUID restaurantId;
    private String name;
    private String cuisine;
    private String street;
    private String postCode;
    private String city;
    private String country;
    private String description;
    private float rating;
    private String slug;
    private String imgUrl;
    private float latitude;
    private float longitude;

    public Restaurant() {
        this.restaurantId = UUID.randomUUID();
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

    public void setName(String name) {
        this.name = name;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(float rating) throws FloatRatingException {
        if (0.0 > rating || rating > 5.0) {
            throw new FloatRatingException(rating);
        } else {
            this.rating = rating;
        }
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public String getCuisine() {
		return this.cuisine;
	}

    public String getStreet() {
        return street;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }

    public float getRating() {
        return rating;
    }

    public String getSlug() {
        return slug;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String toString() {
        return "Restaurant " + "ID: " + restaurantId + " Name: " + name + " Street: " + street + " PostCode: "
                + postCode + " City: " + city + " Country: " + country;
    }
}
