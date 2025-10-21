package com.heshan.mapper;

import com.heshan.model.Branch;
import com.heshan.model.Inventory;
import com.heshan.model.Product;
import com.heshan.payload.dto.InventoryDTO;

public class InventoryMapper {

   public static InventoryDTO toDTO(Inventory inventory) {
      return InventoryDTO.builder()
            .id(inventory.getId())
            .branchId(inventory.getBranch().getId())
            .productId(inventory.getProduct().getId())
            .product(ProductMapper.toDTO(inventory.getProduct()))
            .quantity(inventory.getQuantity())
            .build();
   }

   public static Inventory toEntity(InventoryDTO inventoryDTO, Branch branch, Product product) {
      return Inventory.builder()
            .branch(branch)
            .product(product)
            .quantity(inventoryDTO.getQuantity())
            .build();
   }
}
