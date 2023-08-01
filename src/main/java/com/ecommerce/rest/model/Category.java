package com.ecommerce.rest.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Category")
public class Category {

    private final String name;
}
