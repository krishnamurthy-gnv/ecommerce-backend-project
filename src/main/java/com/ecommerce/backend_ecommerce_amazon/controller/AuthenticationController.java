package com.ecommerce.backend_ecommerce_amazon.controller;

import com.ecommerce.backend_ecommerce_amazon.constants.AuthConstants;
import com.ecommerce.backend_ecommerce_amazon.dto.SignupData;
import com.ecommerce.backend_ecommerce_amazon.entities.User;
import com.ecommerce.backend_ecommerce_amazon.payloads.ApiResponse;
import com.ecommerce.backend_ecommerce_amazon.services.AuthService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth") //request mapping with auth as prefix for this authentication controller
public class AuthenticationController {

    @Autowired
    private AuthService authService; //we are autowiring the AuthService to create the object and use all the functions

    @PostMapping(value = "/signup") // posting the signup data to the server and getting the same object as response
    public ResponseEntity<ApiResponse<User>> signup(@Valid @RequestBody SignupData signupData)
    {
        User newUser = authService.signup(signupData);
        ApiResponse<User> apiResponse = new ApiResponse<User>(true,AuthConstants.SUCCESS_ACCOUNT_CREATED_MSG ,newUser);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        
    }
}
