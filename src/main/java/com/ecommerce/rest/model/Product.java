package com.ecommerce.rest.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Product")
public class Product {

    private String name;
    private String price;
    private String quantity;
    private String category;
}
