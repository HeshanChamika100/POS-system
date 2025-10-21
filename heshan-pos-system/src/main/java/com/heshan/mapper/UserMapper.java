package com.heshan.mapper;

import com.heshan.model.User;
import com.heshan.payload.dto.UserDto;

public class UserMapper {
   public static UserDto toDTO(User savedUser){
      UserDto userDto = new UserDto();
      userDto.setId(savedUser.getId());
      userDto.setEmail(savedUser.getEmail());
      userDto.setRole(savedUser.getRole());
      userDto.setCreatedAt(savedUser.getCreatedAt());
      userDto.setUpdatedAt(savedUser.getUpdatedAt());
      userDto.setLastLogin(savedUser.getLastLogin());
      userDto.setFullName(savedUser.getFullName());
      userDto.setPhone(savedUser.getPhone());
      return userDto;
   }
}
