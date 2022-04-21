package PAP.Restaurantpol.service;
import java.util.List;

import PAP.Restaurantpol.dao.CuisineDAS;
import PAP.Restaurantpol.model.Cuisine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuisineService {
    private final CuisineDAS cuisineDao;

    @Autowired
    public CuisineService(CuisineDAS cuisineDao) {
        this.cuisineDao = cuisineDao;
    }

    public List<Cuisine> getAllCuisines() {
        return cuisineDao.selectAllCuisines();
    }
}
