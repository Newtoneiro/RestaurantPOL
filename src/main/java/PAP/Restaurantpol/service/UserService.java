package PAP.Restaurantpol.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import PAP.Restaurantpol.dao.UserDAS;
import PAP.Restaurantpol.model.User;
import PAP.Restaurantpol.utils.UserType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDAS userDao;

    @Autowired
    public UserService(UserDAS userDao) {
        this.userDao = userDao;
    }

    public Map<String, Object> addUser(User user) {
        var insertResult = userDao.insertUser(user);
        
        if (insertResult == false) {
            return new HashMap<String, Object>();
        }
        else {
            User userData = userDao.selectUserByField("username", user.getUsername());

            Boolean isAdmin = userData.getAdmin() == UserType.ADMIN;

            HashMap<String, Object> map = new HashMap<>();
            map.put("firstName", userData.getFirstName());
            map.put("user_id", userData.getUserId().toString());
            map.put("admin", isAdmin);
            return map;
        }
    }

    public List<User> getAllUsers() {
        return userDao.selectAllUsers();
    }

    public Optional<Map<String, Object>> getUserById(String userId) {
        var user = userDao.getUserInfo(userId);

        if (user.isPresent()) {
            var resultUser = user.get();
            Map<String, Object> map = new HashMap<>();
            map.put("userId", resultUser.getUserId());
            map.put("firstName", resultUser.getFirstName());
            map.put("lastName", resultUser.getLastName());
            map.put("username", resultUser.getUsername());
            map.put("email", resultUser.getEmail());
            map.put("favouriteRestaurant", resultUser.getFavouriteRestaurant());

            return Optional.of(map);
        }
        else {
            return Optional.empty();
        }
    }

    public Boolean deleteUser(String userId) {
        return userDao.deleteUserById(userId);
    }

    public Optional<Boolean> updateUser(User user) {
        var ifChangePassword = !user.getNewPassword().equals("");
        
        if (ifChangePassword) {
            var ifCorrectPassword = userDao.checkUserPassword(user.getUserId(), user.getPassword());
        
            if (!ifCorrectPassword) {
                return Optional.of(false);
            }
            else {
                return userDao.updateUserById(user, true);
            }
        }
        else {
            return userDao.updateUserById(user, false);
        }
    }

    public Optional<Map<String, Object>> checkUser(User user) {
        User userData;
        try {
            userData = userDao.checkUserCredentials(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

        Boolean isAdmin = userData.getAdmin() == UserType.ADMIN;
        
        HashMap<String, Object> map = new HashMap<>();
        map.put("firstName", userData.getFirstName());
        map.put("user_id", userData.getUserId().toString());
        map.put("admin", isAdmin);
        return Optional.of(map);
    }

    public Map<String, Boolean> checkUsernameEmailAvailability(String username, String email) {
        var map = userDao.checkUsernameEmailAvailability(username, email);

        var usernameAvailability = map.get("username").toString().equals("0");
        var emailAvailability = map.get("email").toString().equals("0");

        HashMap<String, Boolean> resultMap = new HashMap<>();
        resultMap.put("username", usernameAvailability);
        resultMap.put("email", emailAvailability);
        
        return resultMap;
    }

}
