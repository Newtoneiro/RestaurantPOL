package PAP.Restaurantpol.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import PAP.Restaurantpol.model.Address;
import PAP.Restaurantpol.service.AddressService;

@RequestMapping("/api/v1/address")
@RestController
@CrossOrigin
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/add")
    public boolean addAddress(@RequestBody Address address) {
        return addressService.addAddress(address);
    }
}
