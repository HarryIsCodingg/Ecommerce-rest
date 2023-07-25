package com.ecommerce.rest.repository;

import com.ecommerce.rest.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, String>{
}
