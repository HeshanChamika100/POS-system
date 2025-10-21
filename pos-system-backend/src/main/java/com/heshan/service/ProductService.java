package com.heshan.service;

import java.util.List;

import com.heshan.model.User;
import com.heshan.payload.dto.ProductDTO;

public interface ProductService {

   ProductDTO createProduct(ProductDTO productDTO, User user) throws Exception;
   ProductDTO updateProduct(Long id, ProductDTO productDTO, User user) throws Exception;
   void deleteProduct(Long id, User user) throws Exception;
   List<ProductDTO> getProductsByStoreId(Long storeId);
   List<ProductDTO> searchByKeyword(Long storeId, String keyword);
}
