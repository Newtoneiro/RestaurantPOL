package PAP.Restaurantpol.api;

import java.util.List;

import PAP.Restaurantpol.model.Cuisine;
import PAP.Restaurantpol.service.CuisineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/cuisine")
@RestController
@CrossOrigin
public class CuisineController {
    
    private final CuisineService cuisineService;

    @Autowired
    public CuisineController(CuisineService cuisineService) {
        this.cuisineService = cuisineService;
    }

    @GetMapping("/all")
    public List<Cuisine> getAllCuisines() {
        return cuisineService.getAllCuisines();
    }

}
