package com.ecommerce.rest.service;


import com.ecommerce.rest.model.Credentials;
import com.ecommerce.rest.model.User;
import com.ecommerce.rest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public boolean validateAdminLogin(Credentials credentials){
        User foundUser = userRepository.findByCredentials_Username(credentials.getUsername());

        return foundUser != null && foundUser.getCredentials().getPassword().equals(credentials.getPassword());
    }
}
