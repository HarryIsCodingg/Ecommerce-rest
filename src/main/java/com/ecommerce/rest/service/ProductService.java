package com.ecommerce.rest.service;

import com.ecommerce.rest.model.Product;
import com.ecommerce.rest.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product deleteProduct(String productName){
        return productRepository.deleteProductByName(productName);
    }

    public boolean saveProduct(Product product){
        final Product savedProduct = productRepository.save(product);
        return !StringUtils.isEmpty(savedProduct);
    }

    public boolean update(String productToUpdate, Product updatedProduct){
        deleteProduct(productToUpdate);
        final Product productUpdated = productRepository.save(updatedProduct);
        return !StringUtils.isEmpty(productUpdated);
    }
}
