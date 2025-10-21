package com.heshan.service;

import java.util.List;

import com.heshan.payload.dto.InventoryDTO;

public interface InventoryService {

   InventoryDTO createInventory(InventoryDTO inventoryDTO) throws Exception;
   InventoryDTO updateInventory(Long id, InventoryDTO inventoryDTO) throws Exception;
   void deleteInventory(Long id);
   InventoryDTO getInventoryById(Long id);
   InventoryDTO getInventoryByProductIdAndBranchId(Long productId, Long branchId);
   List<InventoryDTO> getAllInventoryByBranchId(Long branchId);
}
