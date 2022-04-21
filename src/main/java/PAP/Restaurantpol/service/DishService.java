package PAP.Restaurantpol.service;
import java.util.List;
import java.util.Optional;

import PAP.Restaurantpol.dao.DishDAS;
import PAP.Restaurantpol.model.Dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishService {
    private final DishDAS dishDao;

    @Autowired
    public DishService(DishDAS dishDao) {
        this.dishDao = dishDao;
    }

    public List<Dish> getAllDishes(String restaurant_id) {
        return dishDao.selectAllDishes(restaurant_id);
    }

    public Optional<String> addDish(Dish dish) {
        return dishDao.insertDish(dish);
    }

    public Optional<String> removeDish(String id){
        return dishDao.removeDish(id);
    }
}
