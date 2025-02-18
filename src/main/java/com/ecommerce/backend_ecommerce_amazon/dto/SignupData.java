package com.ecommerce.backend_ecommerce_amazon.dto;

import lombok.Data;


@Data
public class SignupData
{
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
}
