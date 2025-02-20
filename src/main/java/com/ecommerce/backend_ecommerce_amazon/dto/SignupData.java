package com.ecommerce.backend_ecommerce_amazon.dto;

import com.ecommerce.backend_ecommerce_amazon.constants.AuthConstants;
import com.ecommerce.backend_ecommerce_amazon.constants.GenericConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data// @Data for getters and setters
public class SignupData // initializing the signupdata for the user with all the fields necessary matching the db
{
    @NotNull(message = AuthConstants.ERROR_FIRST_NAME_REQUIRED)
    private String firstName;

    @NotNull(message = AuthConstants.ERROR_LAST_NAME_REQUIRED)
    private String lastName;

    @NotNull(message = AuthConstants.ERROR_EMAIL_REQUIRED)
    @Pattern(regexp = GenericConstants.EMAIL_REGEX, message = AuthConstants.ERROR_EMAIL_IS_NOT_VALID)
    private String email;

    @NotNull(message = AuthConstants.ERROR_PASSWORD_REQUIRED)
    @Size(message = AuthConstants.ERROR_PASSWORD_MIN_8_CHARS,min = 8)
    private String password;

    @NotNull(message = AuthConstants.ERROR_PHONE_NUMBER_REQUIRED)
    private String phoneNumber;
}
