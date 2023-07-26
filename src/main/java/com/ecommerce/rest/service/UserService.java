package com.ecommerce.rest.service;


import com.ecommerce.rest.model.Admin;
import com.ecommerce.rest.model.Credentials;
import com.ecommerce.rest.model.User;
import com.ecommerce.rest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User validateUserLogin(Credentials credentials){
        User foundUser = userRepository.findByCredentials_Username(credentials.getUsername());

        if(foundUser != null && foundUser.getCredentials().getPassword().equals(credentials.getPassword())){
            return foundUser;
        }
        return new User();
    }
}
