package com.ecommerce.rest.service;


import com.ecommerce.rest.model.Credentials;
import com.ecommerce.rest.model.Product;
import com.ecommerce.rest.model.User;
import com.ecommerce.rest.repository.ProductRepository;
import com.ecommerce.rest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private ProductRepository productRepository;

    public User validateUserLogin(Credentials credentials){
        User foundUser = userRepository.findByCredentials_Username(credentials.getUsername());

        if(foundUser != null && foundUser.getCredentials().getPassword().equals(credentials.getPassword())){
            return foundUser;
        }
        return new User();
    }

    public List<Product> getProductsByUserUsername(List<String> userProductNames) {


        Set<String> userCategories = productRepository.findByNameIn(userProductNames)
                .stream()
                .map(Product::getCategory)
                .collect(Collectors.toSet());

        return productRepository.findByCategoryInAndNameNotIn(userCategories, userProductNames);
    }
}
