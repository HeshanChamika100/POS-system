package com.heshan.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.heshan.domain.UserRole;
import com.heshan.model.User;
import com.heshan.payload.dto.UserDto;
import com.heshan.payload.response.ApiResponse;
import com.heshan.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

   private final EmployeeService employeeService;

   @PostMapping("/store/{storeId}")
   public ResponseEntity<UserDto> createStoreEmployee(@PathVariable Long storeId, @RequestBody UserDto userDto) throws Exception {
      UserDto employee = employeeService.createStoreEmployee(userDto, storeId);
      return ResponseEntity.ok(employee);
   }

   @PostMapping("/branch/{branchId}")
   public ResponseEntity<UserDto> createBranchEmployee(@PathVariable Long branchId, @RequestBody UserDto userDto) throws Exception {
      UserDto employee = employeeService.createBranchEmployee(userDto, branchId);
      return ResponseEntity.ok(employee);
   }

   @PutMapping("/{id}")
   public ResponseEntity<User> updateEmployee(@PathVariable Long id, @RequestBody UserDto userDto) throws Exception {
      User employee = employeeService.updateEmployee(id, userDto);
      return ResponseEntity.ok(employee);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable Long id) throws Exception {
      employeeService.deleteEmployee(id);
      ApiResponse apiResponse = new ApiResponse();
      apiResponse.setMessage("Employee deleted successfully");
      return ResponseEntity.ok(apiResponse);
   }

   @GetMapping("/store/{id}")
   public ResponseEntity<List<UserDto>> getStoreEmployees(@PathVariable Long id, @RequestParam(required = false) UserRole role) throws Exception {
      List<UserDto> employees = employeeService.findStoreEmployees(id, role);
      return ResponseEntity.ok(employees);
   }

    @GetMapping("/branch/{id}")
   public ResponseEntity<List<UserDto>> getBranchEmployees(@PathVariable Long id, @RequestParam(required = false) UserRole role) throws Exception {
      List<UserDto> employees = employeeService.findBranchEmployees(id, role);
      return ResponseEntity.ok(employees);
   }
}
