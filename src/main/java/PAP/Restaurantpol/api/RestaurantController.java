package PAP.Restaurantpol.api;
import PAP.Restaurantpol.model.Restaurant;

import java.util.List;

import PAP.Restaurantpol.service.RestaurantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/v1/restaurant")
@RestController
@CrossOrigin
public class RestaurantController {
    
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    // @PostMapping("/add")
    // public Optional<String> addRestaurant(@RequestBody Restaurant restaurant) {
    //     return restaurantService.addRestaurant(restaurant);
    // }

    @GetMapping("/all")
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/find")
    public List<Restaurant> getRestaurants(String cuisine, int rating) {
        return restaurantService.getRestaurants(cuisine, rating);
    }

    @GetMapping("/get")
    public Restaurant getRestaurant(String slug) {
        return restaurantService.getRestaurant(slug);
    }

    @GetMapping("/getTemplates")
    public List<String> getTemplates() {
        return restaurantService.getTemplates();
    }

    @PostMapping("/add")
    public boolean addRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.addRestaurant(restaurant);
    }

    @DeleteMapping("/remove")
    public boolean removeRestaurant(String id){
        return restaurantService.removeRestaurant(id);
    }
}
