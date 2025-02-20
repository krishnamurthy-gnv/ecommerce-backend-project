package com.ecommerce.backend_ecommerce_amazon.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.backend_ecommerce_amazon.constants.AuthConstants;
import com.ecommerce.backend_ecommerce_amazon.dto.SignupData;
import com.ecommerce.backend_ecommerce_amazon.entities.User;
import com.ecommerce.backend_ecommerce_amazon.exceptions.UserAlreadyExistsException;
import com.ecommerce.backend_ecommerce_amazon.repo.UserRepo;

@Service
public class AuthService 
{
    @Autowired
    private UserRepo userRepo;
    public User signup(SignupData signupData)
    {
        Optional<User> dbOptional = userRepo.findByEmail(signupData.getEmail());
        if(dbOptional.isPresent())
        {
            throw new UserAlreadyExistsException(AuthConstants.USER_ALREADY_EXISTS);
        }
        User user=new User();
        user.setFirstName(signupData.getFirstName());
        user.setLastName(signupData.getLastName());
        user.setEmail(signupData.getEmail());
        user.setPasswordHash(signupData.getPassword());
        user.setPhoneNumber(signupData.getPhoneNumber());

        return userRepo.save(user);
    }

}
