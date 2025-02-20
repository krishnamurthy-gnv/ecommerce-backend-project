package com.ecommerce.backend_ecommerce_amazon.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend_ecommerce_amazon.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>
{
    Optional<User>findByEmail(String email);
    

}
