package com.ecommerce.rest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, String> extends MongoRepository<T, String> {

    T findByCredentials_Username(String username);
}
