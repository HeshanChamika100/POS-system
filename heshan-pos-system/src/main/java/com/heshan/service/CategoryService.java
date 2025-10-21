package com.heshan.service;

import java.util.List;

import com.heshan.payload.dto.CategoryDTO;

public interface CategoryService {

   CategoryDTO createCategory(CategoryDTO categoryDTO) throws Exception;
   List<CategoryDTO> getCategoriesByStore(Long storeId);
   CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) throws Exception;
   void deleteCategory(Long id) throws Exception;
}
