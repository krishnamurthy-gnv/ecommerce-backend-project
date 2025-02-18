package com.ecommerce.backend_ecommerce_amazon.controller;

import com.ecommerce.backend_ecommerce_amazon.dto.SignupData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @PostMapping(value = "/signup")
    public SignupData signup(@RequestBody SignupData signupData)
    {
        return signupData;
    }
}
