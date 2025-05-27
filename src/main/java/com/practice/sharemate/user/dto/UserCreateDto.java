package com.practice.sharemate.user.dto;

import com.practice.sharemate.validators.annotations.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateDto {

    @NotBlank
    @Email
    @UniqueEmail
    private String email;

    @Size(min=1)
    private String name;

}

