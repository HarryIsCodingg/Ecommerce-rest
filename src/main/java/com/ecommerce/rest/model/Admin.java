package com.ecommerce.rest.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@Document(collection= "Admin")
public class Admin {

    private Credentials credentials;
}
