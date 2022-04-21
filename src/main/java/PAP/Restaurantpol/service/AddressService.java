package PAP.Restaurantpol.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PAP.Restaurantpol.dao.AddressDAS;
import PAP.Restaurantpol.model.Address;

@Service
public class AddressService {
    private final AddressDAS addressDao;

    @Autowired
    public AddressService(AddressDAS addressDao) {
        this.addressDao = addressDao;
    }

    public boolean addAddress(Address address) {
        return addressDao.addAddress(address);
    }
}
