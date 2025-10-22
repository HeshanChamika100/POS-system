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
      userDto.setStoreId(savedUser.getStore() != null ? savedUser.getStore().getId() : null);
      userDto.setBranchId(savedUser.getBranch() != null ? savedUser.getBranch().getId() : null);

      return userDto;
   }

   public static User toEntity(UserDto userDto) {
      User createdUser = new User();
      // createdUser.setId(userDto.getId());
      createdUser.setEmail(userDto.getEmail());
      createdUser.setFullName(userDto.getFullName());
      createdUser.setPhone(userDto.getPhone());
      createdUser.setRole(userDto.getRole());
      createdUser.setPassword(userDto.getPassword());
      createdUser.setCreatedAt(userDto.getCreatedAt());
      createdUser.setUpdatedAt(userDto.getUpdatedAt());
      createdUser.setLastLogin(userDto.getLastLogin());

      return createdUser;
   }
}
