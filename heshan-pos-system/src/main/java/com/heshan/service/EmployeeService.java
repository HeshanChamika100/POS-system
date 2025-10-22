package com.heshan.service;

import java.util.List;

import com.heshan.domain.UserRole;
import com.heshan.model.User;
import com.heshan.payload.dto.UserDto;

public interface EmployeeService {

   UserDto createStoreEmployee(UserDto employee, Long storeId) throws Exception;
   UserDto createBranchEmployee(UserDto employee, Long branchId) throws Exception;
   User updateEmployee(Long employeeId, UserDto employeeDetails) throws Exception;
   void deleteEmployee(Long employeeId) throws Exception;
   List<UserDto> findStoreEmployees(Long storeId, UserRole role) throws Exception;
   List<UserDto> findBranchEmployees(Long branchId, UserRole role) throws Exception;
}
