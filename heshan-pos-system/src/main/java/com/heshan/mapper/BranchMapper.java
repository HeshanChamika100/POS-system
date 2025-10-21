package com.heshan.mapper;

import java.time.LocalDateTime;

import com.heshan.model.Branch;
import com.heshan.model.Store;
import com.heshan.payload.dto.BranchDTO;

public class BranchMapper {

   public static BranchDTO toDTO(Branch branch) {
      return BranchDTO.builder()
            .id(branch.getId())
            .name(branch.getName())
            .address(branch.getAddress())
            .phone(branch.getPhone())
            .email(branch.getEmail())
            .workingDays(branch.getWorkingDays())
            .openTime(branch.getOpenTime())
            .closeTime(branch.getCloseTime())
            .createdAt(branch.getCreatedAt())
            .updatedAt(branch.getUpdatedAt())
            .storeId(branch.getStore()!=null?branch.getStore().getId():null)
            .manager(branch.getManager()!=null?UserMapper.toDTO(branch.getManager()):null)
            .build();
   }

   public static Branch toEntity(BranchDTO branchDTO, Store store) {
      return Branch.builder()
            .name(branchDTO.getName())
            .address(branchDTO.getAddress())
            .store(store)
            .email(branchDTO.getEmail())
            .phone(branchDTO.getPhone())
            .openTime(branchDTO.getOpenTime())
            .closeTime(branchDTO.getCloseTime())
            .workingDays(branchDTO.getWorkingDays())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
   }
}
