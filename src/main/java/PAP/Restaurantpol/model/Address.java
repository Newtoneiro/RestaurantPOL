package PAP.Restaurantpol.model;

import java.util.UUID;

public class Address {
    private UUID address_id;
    private String street;
    private String post_code;
    private String city;
    private String country;
    private float latitude;
    private float longitude;

    public Address(){
		this.address_id = UUID.randomUUID();
    }

    public Address(UUID address_id, String street, String post_code, String city, String country, float latitude, float longitude){
        this.address_id = address_id;
        this.street = street;
        this.post_code = post_code;
        this.city = city;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

	public UUID getAddress_id() {
		return this.address_id;
	}

	public void setAddress_id(UUID address_id) {
		this.address_id = address_id;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPost_code() {
		return this.post_code;
	}

	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public float getLatitude() {
		return this.latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return this.longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

    public String toString(){
        return "ID: " + address_id + " Street: " + street + " Post code: " + post_code + " City: " + city + " country: " + country + " longitude: " + longitude + " latitude " + latitude;
    }

}
