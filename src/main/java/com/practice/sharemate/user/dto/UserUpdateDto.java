package com.practice.sharemate.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateDto {

    @Email
    private String email;

    @Size(min=1)
    private String name;
}
