package com.heshan.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.heshan.domain.UserRole;
import com.heshan.mapper.UserMapper;
import com.heshan.model.Branch;
import com.heshan.model.Store;
import com.heshan.model.User;
import com.heshan.payload.dto.UserDto;
import com.heshan.repository.BranchRepository;
import com.heshan.repository.StoreRepository;
import com.heshan.repository.UserRepository;
import com.heshan.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

   private final UserRepository userRepository;
   private final StoreRepository storeRepository;
   private final BranchRepository branchRepository;
   private final PasswordEncoder passwordEncoder;

   @Override
   public UserDto createStoreEmployee(UserDto employee, Long storeId) throws Exception {
      Store store = storeRepository.findById(storeId).orElseThrow(() -> new Exception("Store not found"));

      Branch branch = null;

      if (employee.getRole() == UserRole.ROLE_BRANCH_MANAGER) {
         if (employee.getBranchId() == null) {
            throw new Exception("Branch ID is required to create branch manager");
         }
         branch = branchRepository.findById(employee.getBranchId())
               .orElseThrow(() -> new Exception("Branch not found"));
      }
      User user = UserMapper.toEntity(employee);
      user.setStore(store);
      user.setBranch(branch);
      user.setPassword(passwordEncoder.encode(employee.getPassword()));

      User savedEmployee = userRepository.save(user);
      if (employee.getRole() == UserRole.ROLE_BRANCH_MANAGER && branch != null) {
         branch.setManager(savedEmployee);
         branchRepository.save(branch);
      }
      return UserMapper.toDTO(savedEmployee);
   }

   @Override
   public UserDto createBranchEmployee(UserDto employee, Long branchId) throws Exception {

      Branch branch = branchRepository.findById(branchId)
            .orElseThrow(() -> new Exception("Branch not found"));

      if (employee.getRole() == UserRole.ROLE_BRANCH_CASHIER || employee.getRole() == UserRole.ROLE_BRANCH_MANAGER) {

         User user = UserMapper.toEntity(employee);
         user.setBranch(branch);
         user.setPassword(passwordEncoder.encode(employee.getPassword()));
         return UserMapper.toDTO(userRepository.save(user));
      }
      throw new Exception("Branch role not supported");
   }

   @Override
   public User updateEmployee(Long employeeId, UserDto employeeDetails) throws Exception {

      User existingEmployee = userRepository.findById(employeeId)
            .orElseThrow(() -> new Exception("Employee not exist with id: " + employeeId));

      Branch branch = branchRepository.findById(employeeDetails.getBranchId())
            .orElseThrow(() -> new Exception("Branch not found"));

      existingEmployee.setEmail(employeeDetails.getEmail());
      existingEmployee.setFullName(employeeDetails.getFullName());
      existingEmployee.setPassword(employeeDetails.getPassword());
      existingEmployee.setRole(employeeDetails.getRole());
      existingEmployee.setBranch(branch);
      return userRepository.save(existingEmployee);
   }

   @Override
   public void deleteEmployee(Long employeeId) throws Exception {

      User employee = userRepository.findById(employeeId)
            .orElseThrow(() -> new Exception("Employee not exist with id: " + employeeId));
      userRepository.delete(employee);
   }

   @Override
   public List<UserDto> findStoreEmployees(Long storeId, UserRole role) throws Exception {
      Store store = storeRepository.findById(storeId).orElseThrow(() -> new Exception("Store not found"));

      return userRepository.findByStore(store).stream().filter(user -> role == null || user.getRole() == role).map(UserMapper::toDTO)
            .collect(Collectors.toList());
   }

   @Override
   public List<UserDto> findBranchEmployees(Long branchId, UserRole role) throws Exception {
      Branch branch = branchRepository.findById(branchId).orElseThrow(() -> new Exception("Branch not found"));

      return userRepository.findByBranchId(branchId).stream()
            .filter(user -> role == null || user.getRole() == role).map(UserMapper::toDTO).collect(Collectors.toList());
   }

}
