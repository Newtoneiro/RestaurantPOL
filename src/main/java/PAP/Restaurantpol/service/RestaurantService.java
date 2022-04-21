package PAP.Restaurantpol.service;
import java.util.List;
import java.util.regex.Pattern;
import java.util.Locale;
import java.text.Normalizer;

import PAP.Restaurantpol.dao.AddressDAS;
import PAP.Restaurantpol.dao.RestaurantDAS;
import PAP.Restaurantpol.model.Address;
import PAP.Restaurantpol.model.Restaurant;
import PAP.Restaurantpol.model.Restaurant_instance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    private final RestaurantDAS restaurantDao;
    private final AddressDAS addressDao;

    @Autowired
    public RestaurantService(RestaurantDAS restaurantDao, AddressDAS addressDao) {
        this.restaurantDao = restaurantDao;
        this.addressDao = addressDao;
    }

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    private static final Pattern EDGESDHASHES = Pattern.compile("(^-|-$)");
    
    public String toSlug(String restaurantName) {
        String nowhitespace = WHITESPACE.matcher(restaurantName).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        slug = EDGESDHASHES.matcher(slug).replaceAll("");
        slug = slug.toLowerCase(Locale.ENGLISH);
        return slug;
    }

    // public Optional<String> addRestaurant(Restaurant restaurant) {
    //     Address address = new Address(restaurant.getStreet(), restaurant.getPostCode(), restaurant.getCity(), restaurant.getCountry());
    //     Optional<String> addressId = addressDao.addAddress(address);
    //     if (addressId.isPresent()) {
    //         restaurant.setAddressId(addressId.get());
    //         restaurant.setSlug(toSlug(restaurant.getName()));
    //         return restaurantDao.addRestaurant(restaurant);
    //     }
    //     else {
    //         return Optional.empty();
    //     }
    // }

    public List<Restaurant> getAllRestaurants() {
        return restaurantDao.selectAllRestaurants();
    }

    public List<Restaurant> getRestaurants(String cuisine, int rating) {
        return restaurantDao.selectRestaurants(cuisine, rating);
    }

    public Restaurant getRestaurant(String slug) {
        return restaurantDao.selectRestaurant(slug.replace("\'", "\'\'"));
    }

    public List<String> getTemplates(){
        return restaurantDao.getTemplates();
    }

    public boolean addRestaurant(Restaurant restaurant){
        Address address = new Address();
        address.setCity(restaurant.getCity());
        address.setCountry(restaurant.getCountry());
        address.setPost_code(restaurant.getPostCode());
        address.setStreet(restaurant.getStreet());
        address.setLatitude(restaurant.getLatitude());
        address.setLongitude(restaurant.getLongitude());
        
        String r_t_id;
        r_t_id = restaurantDao.getTemplateId(restaurant.getName().replace("\'", "\'\'"));
        
        Restaurant_instance r_i = new Restaurant_instance();
        r_i.setAddress_id(address.getAddress_id().toString());
        r_i.setSlug(restaurant.getSlug());
        r_i.setRestaurant_template_id(r_t_id);

        return addressDao.addAddress(address) && restaurantDao.addRestaurantInstance(r_i);
    }

    public boolean removeRestaurant(String id){
        return restaurantDao.removeRestaurant(id);
    }
}
