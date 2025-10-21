package com.heshan.service;

import java.util.List;

import com.heshan.exceptions.UserException;
import com.heshan.model.User;

public interface UserService {

   User getUserFromJwtToken(String token) throws UserException;
   User getCurrentUser() throws UserException;
   User getUserByEmail(String email) throws UserException;
   User getUserById(Long id) throws Exception;
   List<User> getAllUsers() throws UserException;
}
