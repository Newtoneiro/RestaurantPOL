package PAP.Restaurantpol.api;

import java.util.List;
import java.util.Optional;

import PAP.Restaurantpol.model.Dish;
import PAP.Restaurantpol.service.DishService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/dish")
@RestController
@CrossOrigin
public class DishController {
    
    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/all")
    public List<Dish> getAllDishes(String restaurant_id) {
        return dishService.getAllDishes(restaurant_id);
    }

    @PostMapping("/add")
    public Optional<String> addDish(@RequestBody Dish dish) {
        return dishService.addDish(dish);
    }

    @DeleteMapping("/remove")
    public Optional<String> removeDish(String id){
        return dishService.removeDish(id);
    }
}
