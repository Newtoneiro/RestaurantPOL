package PAP.Restaurantpol.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import PAP.Restaurantpol.model.User;
import PAP.Restaurantpol.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/user")
@RestController
@CrossOrigin
public class UserController {
    
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public Map<String, Object> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public Optional<Map<String, Object>> getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/{userId}")
    public Boolean deleteUserById(@PathVariable String userId) {
        return userService.deleteUser(userId);
    }

    @PutMapping("/edit")
    public Optional<Boolean> updateUser(@RequestBody User userToUpdate) {
        return userService.updateUser(userToUpdate);
    }

    @PostMapping("/auth")
    public Optional<Map<String, Object>> checkUser(@RequestBody User userToAuth) {
        return userService.checkUser(userToAuth);
    }

    @GetMapping("/checkUsernameEmail")
    public Map<String, Boolean> checkUsername(@RequestParam String username, @RequestParam String email) {
        return userService.checkUsernameEmailAvailability(username, email);
    }

}
