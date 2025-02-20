package com.ecommerce.backend_ecommerce_amazon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    @GetMapping("/") //initializing the root controller with a welcome message
    public String getMethodName()
    {
        return "Welcome to E-Commerce Backend project";
    }


}
