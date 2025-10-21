package com.heshan.mapper;

import com.heshan.model.Product;
import com.heshan.model.Store;
import com.heshan.payload.dto.ProductDTO;

public class ProductMapper {

   public static ProductDTO toDTO(Product product) {
      return ProductDTO.builder().id(product.getId()).name(product.getName()).sku(product.getSku())
            .description(product.getDescription())
            .mrp(product.getMrp()).sellingPrice(product.getSellingPrice()).brand(product.getBrand())
            .image(product.getImage()).category(CategoryMapper.toDTO(product.getCategory()))
            .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
            .storeId(product.getStore() != null ? product.getStore().getId() : null).createdAt(product.getCreatedAt())
            .updatedAt(product.getUpdatedAt()).build();
   }

   public static Product toEntity(ProductDTO productDTO, Store store, com.heshan.model.Category category) {
      return Product.builder().name(productDTO.getName()).sku(productDTO.getSku())
            .description(productDTO.getDescription()).mrp(productDTO.getMrp())
            .sellingPrice(productDTO.getSellingPrice()).brand(productDTO.getBrand())
            .image(productDTO.getImage()).store(store).category(category).build();
   }
}