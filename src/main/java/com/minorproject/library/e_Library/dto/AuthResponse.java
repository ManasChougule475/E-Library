package com.minorproject.library.e_Library.dto;

import org.springframework.security.core.userdetails.UserDetails;

public class AuthResponse {
    private UserDetails user;
    private String token;

    public AuthResponse(UserDetails user,String token){
        this.user = user;
        this.token = token;
    }

    public UserDetails getUser(){
        return this.user;
    }

    public String getToken(){
        return this.token;
    }
}
