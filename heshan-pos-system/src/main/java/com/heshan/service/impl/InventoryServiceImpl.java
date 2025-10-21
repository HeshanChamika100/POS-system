package com.heshan.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.heshan.mapper.InventoryMapper;
import com.heshan.model.Branch;
import com.heshan.model.Inventory;
import com.heshan.model.Product;
import com.heshan.payload.dto.InventoryDTO;
import com.heshan.repository.BranchRepository;
import com.heshan.repository.InventoryRepository;
import com.heshan.repository.ProductRepository;
import com.heshan.service.InventoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

   private final InventoryRepository inventoryRepository;
   private final BranchRepository branchRepository;
   private final ProductRepository productRepository;

   @Override
   public InventoryDTO createInventory(InventoryDTO inventoryDTO) throws Exception {
      Branch branch = branchRepository.findById(inventoryDTO.getBranchId())
            .orElseThrow(() -> new Exception("Branch not exist.."));

      Product product = productRepository.findById(inventoryDTO.getProductId())
            .orElseThrow(() -> new Exception("Product not exist.."));

      Inventory inventory = InventoryMapper.toEntity(inventoryDTO, branch, product);
      Inventory savedInventory = inventoryRepository.save(inventory);
      return InventoryMapper.toDTO(savedInventory);
   }

   @Override
   public InventoryDTO updateInventory(Long id, InventoryDTO inventoryDTO) throws Exception {

      Inventory inventory = inventoryRepository.findById(id)
            .orElseThrow(() -> new Exception("Inventory not found.."));

      inventory.setQuantity(inventoryDTO.getQuantity());
      Inventory updatedInventory = inventoryRepository.save(inventory);
      return InventoryMapper.toDTO(updatedInventory);
   }

   @Override
   public void deleteInventory(Long id) throws Exception {
      
      Inventory inventory = inventoryRepository.findById(id)
            .orElseThrow(() -> new Exception("Inventory not found.."));

      inventoryRepository.delete(inventory);
   }

   @Override
   public InventoryDTO getInventoryById(Long id) throws Exception {
      
      Inventory inventory = inventoryRepository.findById(id)
            .orElseThrow(() -> new Exception("Inventory not found.."));
      
      return InventoryMapper.toDTO(inventory);
   }

   @Override
   public InventoryDTO getInventoryByProductIdAndBranchId(Long productId, Long branchId) {
      
      Inventory inventory = inventoryRepository.findByProductIdAndBranchId(productId, branchId);

      return InventoryMapper.toDTO(inventory);
   }

   @Override
   public List<InventoryDTO> getAllInventoryByBranchId(Long branchId) {
      List<Inventory> inventories = inventoryRepository.findByBranchId(branchId);
      return inventories.stream().map(InventoryMapper::toDTO).collect(Collectors.toList());
   }

}
