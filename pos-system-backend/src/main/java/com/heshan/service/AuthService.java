package com.heshan.service;

import com.heshan.exceptions.UserException;
import com.heshan.payload.dto.UserDto;
import com.heshan.payload.response.AuthResponse;

public interface AuthService {
   AuthResponse signup(UserDto userDto) throws UserException;
   AuthResponse login(UserDto userDto) throws UserException;
}
