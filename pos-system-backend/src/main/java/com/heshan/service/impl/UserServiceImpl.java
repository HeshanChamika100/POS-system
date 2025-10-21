package com.heshan.service.impl;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.heshan.configuration.JwtProvider;
import com.heshan.exceptions.UserException;
import com.heshan.model.User;
import com.heshan.repository.UserRepository;
import com.heshan.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;
   private final JwtProvider jwtProvider;

   @Override
   public User getUserFromJwtToken(String token) throws UserException {
      
      String email = jwtProvider.getEmailFromToken(token);
      User user = userRepository.findByEmail(email);
      if (user == null) {
         throw new UserException("Invalid Token");
      }
      return user;
   }

   @Override
   public User getCurrentUser() throws UserException {
      
      String email = SecurityContextHolder.getContext().getAuthentication().getName();
      User user = userRepository.findByEmail(email);
      if (user == null) {
         throw new UserException("User not found");
      }
      return user;
   }

   @Override
   public User getUserByEmail(String email) throws UserException {
      User user = userRepository.findByEmail(email);
      if (user == null) {
         throw new UserException("User not found");
      }
      return user;
   }

   @Override
   public User getUserById(Long id) throws Exception {
      return userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
   }

   @Override
   public List<User> getAllUsers() {
      return userRepository.findAll();
   }
   
}
