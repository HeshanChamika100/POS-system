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
import org.springframework.web.bind.annotation.RestController;

import com.heshan.exceptions.UserException;
import com.heshan.payload.dto.BranchDTO;
import com.heshan.payload.response.ApiResponse;
import com.heshan.service.BranchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/branches")
public class BranchController {

   private final BranchService branchService;

   @PostMapping
   public ResponseEntity<BranchDTO> createBranch(@RequestBody BranchDTO branchDTO) throws UserException {
      BranchDTO createdBranch = branchService.createBranch(branchDTO);
      return ResponseEntity.ok(createdBranch);
   }

   @GetMapping("/{id}")
   public ResponseEntity<BranchDTO> getBranchById(@PathVariable Long id) throws Exception {
      BranchDTO branch = branchService.getBranchById(id);
      return ResponseEntity.ok(branch);
   }

   @GetMapping("/store/{storeId}")
   public ResponseEntity<List<BranchDTO>> getAllBranchesByStoreId(@PathVariable Long storeId) {
      List<BranchDTO> branches = branchService.getAllBranchesByStoreId(storeId);
      return ResponseEntity.ok(branches);
   }

   @PutMapping("/{id}")
   public ResponseEntity<BranchDTO> updateBranch(@PathVariable Long id, @RequestBody BranchDTO branchDTO) throws Exception {
      BranchDTO updatedBranch = branchService.updateBranch(id, branchDTO);
      return ResponseEntity.ok(updatedBranch);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<ApiResponse> deleteBranch(@PathVariable Long id) throws Exception {
      branchService.deleteBranch(id);
      ApiResponse apiResponse = new ApiResponse();
      apiResponse.setMessage("Branch deleted successfully");
      return ResponseEntity.ok(apiResponse);
   }
}
