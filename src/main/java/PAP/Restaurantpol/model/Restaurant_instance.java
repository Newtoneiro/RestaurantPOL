package PAP.Restaurantpol.model;

import java.util.UUID;

public class Restaurant_instance {
    private UUID restaurant_instance_id;
    private String address_id;
    private String slug;
    private String restaurant_template_id;

    public Restaurant_instance(){
        this.restaurant_instance_id = UUID.randomUUID();
    }

    public Restaurant_instance(UUID restaurant_instance_id, String address_id, float rating, String slug, String restaurant_template_id){
        this.restaurant_instance_id = restaurant_instance_id;
        this.address_id = address_id;
        this.slug = slug;
        this.restaurant_template_id = restaurant_template_id;
    }

	public UUID getRestaurant_instance_id() {
		return this.restaurant_instance_id;
	}

	public void setRestaurant_instance_id(UUID restaurant_instance_id) {
		this.restaurant_instance_id = restaurant_instance_id;
	}

	public String getAddress_id() {
		return this.address_id;
	}

	public void setAddress_id(String address_id) {
		this.address_id = address_id;
	}

	public String getSlug() {
		return this.slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getRestaurant_template_id() {
		return this.restaurant_template_id;
	}

	public void setRestaurant_template_id(String restaurant_template_id) {
		this.restaurant_template_id = restaurant_template_id;
	}

    public String toString(){
        return "ID: " + restaurant_instance_id + ' ' + address_id + ' ' + slug + ' ' + restaurant_template_id;
    }

}
