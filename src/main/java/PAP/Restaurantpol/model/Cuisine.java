package PAP.Restaurantpol.model;

public class Cuisine {

    private String cuisineId;
    private String name;

    public Cuisine() {
    }

    public Cuisine(String cuisineId, String name) {
        this.cuisineId = cuisineId;
        this.name = name;
    }

    public String getCuisineId() {
        return cuisineId;
    }

    public void setCuisineId(String cuisineId) {
        this.cuisineId = cuisineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cuisine{" +
                "id=" + cuisineId +
                ", name='" + name + '\'' +
                '}';
    }
    
}
