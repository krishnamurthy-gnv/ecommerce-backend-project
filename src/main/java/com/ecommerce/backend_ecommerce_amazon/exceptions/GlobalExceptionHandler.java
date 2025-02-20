package com.ecommerce.backend_ecommerce_amazon.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.backend_ecommerce_amazon.constants.ExceptionConstants;
import com.ecommerce.backend_ecommerce_amazon.payloads.ApiResponse;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String,String>>> handleValidationErrors(MethodArgumentNotValidException ex)
    {
        Map<String,String> errorsMap = new HashMap<String, String>();

        ex.getBindingResult().getFieldErrors().forEach(
                error ->{
                    errorsMap.put(error.getField(), error.getDefaultMessage());
                }
        );

        ApiResponse<Map<String,String>> response  = new ApiResponse<>(false,ExceptionConstants.API_FAILED,errorsMap);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleExceptions(Exception ex)
    {
        ApiResponse<String> response  = new ApiResponse<>(false,ExceptionConstants.UNABLE_TO_PROCESS_REQUEST,ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
