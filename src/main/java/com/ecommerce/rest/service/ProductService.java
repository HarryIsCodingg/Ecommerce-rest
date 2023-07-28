package com.ecommerce.rest.service;

import com.ecommerce.rest.model.Product;
import com.ecommerce.rest.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
