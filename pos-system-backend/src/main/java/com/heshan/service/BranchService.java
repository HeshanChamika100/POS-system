package com.heshan.service;

import java.util.List;

import com.heshan.exceptions.UserException;
import com.heshan.payload.dto.BranchDTO;

public interface BranchService {

   BranchDTO createBranch(BranchDTO branchDTO) throws UserException;
   BranchDTO updateBranch(Long id, BranchDTO branchDTO) throws Exception;
   void deleteBranch(Long id) throws Exception;
   List<BranchDTO> getAllBranchesByStoreId(Long storeId);
   BranchDTO getBranchById(Long id) throws Exception;
}
