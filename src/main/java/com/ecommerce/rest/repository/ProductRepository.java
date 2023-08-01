package com.ecommerce.rest.repository;

import com.ecommerce.rest.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByNameIn(List<String> productNames);
    List<Product> findByCategoryInAndNameNotIn(Set<String> userCategories, List<String> userProductNames);
    Product deleteProductByName(String productName);
    Product findByName(String name);
}
