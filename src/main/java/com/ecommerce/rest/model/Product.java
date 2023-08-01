package com.ecommerce.rest.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Product")
public class Product {

    private String name;
    private String quantity;
    private String category;
    private String imageUrl;
    private String pricePerPound;

}
