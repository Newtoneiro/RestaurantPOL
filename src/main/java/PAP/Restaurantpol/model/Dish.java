package PAP.Restaurantpol.model;

public class Dish {
    
    private String dish_id;
    private String name;
    private String description;
    private float price;
    private String imgUrl;
    private String restaurantId;

    public Dish() {
    }

    public Dish(String dish_id, String name, String description, float price, String imgUrl, String restaurantId) {
        this.dish_id = dish_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public String getdish_id() {
        return dish_id;
    }

    public void setdish_id(String dish_id) {
        this.dish_id = dish_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "Dishes{" + "id=" + dish_id + ", name=" + name + ", description=" + description + ", price=" + price + ", imaUrl=" + imgUrl + '}';
    }

}
