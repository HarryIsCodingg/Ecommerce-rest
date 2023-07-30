package com.ecommerce.rest.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "User")
public class User {

    private Credentials credentials;
    private boolean hasCustomBasket;
    private List<Product> productList;
    private int coupons;
}
