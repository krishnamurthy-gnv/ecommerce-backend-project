package com.ecommerce.backend_ecommerce_amazon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    @GetMapping("/")
    public String getMethodName()
    {
        return "Welcome to E-Commerce Backend project";
    }


}
