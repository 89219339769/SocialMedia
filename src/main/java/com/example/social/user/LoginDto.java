package com.example.social.user;

import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String password;

    private String email;
}