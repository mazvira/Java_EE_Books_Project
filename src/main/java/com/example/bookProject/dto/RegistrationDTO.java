package com.example.bookProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {

    @NotEmpty(message = "You should write your login")
    @Pattern(regexp = "^[A-Za-z0-9]+$",
            message = "Login must have only latin letters and numbers")
    private String login;

    @NotEmpty(message = "You should write your password")
    @Size(min = 8, max = 20,
            message = "Password must be between 8 and 20 characters")
    private String password;
}
