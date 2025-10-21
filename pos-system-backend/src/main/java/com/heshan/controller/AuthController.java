package com.heshan.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heshan.exceptions.UserException;
import com.heshan.payload.dto.UserDto;
import com.heshan.payload.response.AuthResponse;
import com.heshan.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
   private final AuthService authService;

   @PostMapping("/signup")
   public ResponseEntity<AuthResponse> signupHandler(
      @RequestBody UserDto userDto
   ) throws UserException{
      return ResponseEntity.ok(authService.signup(userDto));
   }

   @PostMapping("/login")
   public ResponseEntity<AuthResponse> loginHandler(
      @RequestBody UserDto userDto
   ) throws UserException{
      return ResponseEntity.ok(authService.login(userDto));
   }
}
