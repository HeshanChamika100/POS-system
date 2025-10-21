package com.heshan.payload.response;

import com.heshan.payload.dto.UserDto;

import lombok.Data;

@Data
public class AuthResponse {
   private String jwt;
   private String message;
   private UserDto user;
}
