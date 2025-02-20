package com.ecommerce.backend_ecommerce_amazon.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse <T> 
{
    private boolean success;
    private String message;
    private T data;
}
