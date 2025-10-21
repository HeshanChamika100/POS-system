package com.heshan.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.heshan.mapper.ProductMapper;
import com.heshan.model.Category;
import com.heshan.model.Product;
import com.heshan.model.Store;
import com.heshan.model.User;
import com.heshan.payload.dto.ProductDTO;
import com.heshan.repository.CategoryRepository;
import com.heshan.repository.ProductRepository;
import com.heshan.repository.StoreRepository;
import com.heshan.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

   private final ProductRepository productRepository;
   private final StoreRepository storeRepository;
   private final CategoryRepository categoryRepository;

   @Override
   public ProductDTO createProduct(ProductDTO productDTO, User user) throws Exception {

      Store store = storeRepository.findById(productDTO.getStoreId())
            .orElseThrow(() -> new Exception("Store not found"));

      Category category = categoryRepository.findById(productDTO.getCategoryId())
            .orElseThrow(() -> new Exception("Category not found"));

      Product product = ProductMapper.toEntity(productDTO, store, category);
      Product savedProduct = productRepository.save(product);

      return ProductMapper.toDTO(savedProduct);
   }

   @Override
   public ProductDTO updateProduct(Long id, ProductDTO productDTO, User user) throws Exception {
      Product product = productRepository.findById(id).orElseThrow(() -> new Exception("Product not found"));

      if (productDTO.getCategoryId() != null) {
         Category category = categoryRepository.findById(productDTO.getCategoryId())
               .orElseThrow(() -> new Exception("Category not found"));

         product.setCategory(category);
      }

      product.setName(productDTO.getName());
      product.setDescription(productDTO.getDescription());
      product.setSku(productDTO.getSku());
      product.setImage(productDTO.getImage());
      product.setMrp(productDTO.getMrp());
      product.setSellingPrice(productDTO.getSellingPrice());
      product.setBrand(productDTO.getBrand());
      product.setUpdatedAt(LocalDateTime.now());

      Product updatedProduct = productRepository.save(product);
      return ProductMapper.toDTO(updatedProduct);
   }

   @Override
   public void deleteProduct(Long id, User user) throws Exception {

      Product product = productRepository.findById(id).orElseThrow(() -> new Exception("Product not found"));
      productRepository.delete(product);
   }

   @Override
   public List<ProductDTO> getProductsByStoreId(Long storeId) {

      List<Product> products = productRepository.findByStoreId(storeId);
      return products.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
   }

   @Override
   public List<ProductDTO> searchByKeyword(Long storeId, String keyword) {

      List<Product> products = productRepository.searchByKeyword(storeId, keyword);
      return products.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
   }

}
