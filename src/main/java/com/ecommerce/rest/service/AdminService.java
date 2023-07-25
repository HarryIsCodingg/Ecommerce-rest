package com.ecommerce.rest.service;

import com.ecommerce.rest.model.Admin;
import com.ecommerce.rest.model.Credentials;
import com.ecommerce.rest.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminService {

    private AdminRepository adminRepository;

    public boolean validateAdminLogin(Credentials credentials){
        Admin foundUser = adminRepository.findByCredentials_Username(credentials.getUsername());

        return foundUser != null && foundUser.getCredentials().getPassword().equals(credentials.getPassword());
    }
}
