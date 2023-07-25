package com.ecommerce.rest.repository;

import com.ecommerce.rest.model.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends BaseRepository<Admin, String> {
}
