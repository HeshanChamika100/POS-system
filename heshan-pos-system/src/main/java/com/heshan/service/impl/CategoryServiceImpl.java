package com.heshan.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.heshan.domain.UserRole;
import com.heshan.mapper.CategoryMapper;
import com.heshan.model.Category;
import com.heshan.model.Store;
import com.heshan.model.User;
import com.heshan.payload.dto.CategoryDTO;
import com.heshan.repository.CategoryRepository;
import com.heshan.repository.StoreRepository;
import com.heshan.service.CategoryService;
import com.heshan.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

   private final CategoryRepository categoryRepository;
   private final UserService userService;
   private final StoreRepository storeRepository;

   @Override
   public CategoryDTO createCategory(CategoryDTO categoryDTO) throws Exception {

      User user = userService.getCurrentUser();

      Store store = storeRepository.findById(categoryDTO.getStoreId()).orElseThrow(() -> new Exception("Store not found"));

      Category category = Category.builder().store(store).name(categoryDTO.getName()).build();

      checkAuthority(user, category.getStore());

      return CategoryMapper.toDTO(categoryRepository.save(category));
   }

   @Override
   public List<CategoryDTO> getCategoriesByStore(Long storeId) {

      List<Category> categories = categoryRepository.findByStoreId(storeId);
      return categories.stream().map(CategoryMapper::toDTO).collect(Collectors.toList());
   }

   @Override
   public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) throws Exception {
      
      Category category = categoryRepository.findById(id).orElseThrow(() -> new Exception("Category not found"));

      User user = userService.getCurrentUser();

      category.setName(categoryDTO.getName());
      checkAuthority(user, category.getStore());
      return CategoryMapper.toDTO(categoryRepository.save(category));
   }

   @Override
   public void deleteCategory(Long id) throws Exception {
      Category category = categoryRepository.findById(id).orElseThrow(() -> new Exception("Category not found"));

      User user = userService.getCurrentUser();

      checkAuthority(user, category.getStore());
      categoryRepository.delete(category);
   }

   private void checkAuthority(User user, Store store) throws Exception {
      boolean isAdmin = user.getRole().equals(UserRole.ROLE_STORE_ADMIN);
      boolean isManager = user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
      boolean isSameStore = user.equals(store.getStoreAdmin());

      if (!(isAdmin && isSameStore) && !isManager) {
         throw new Exception("You don't have permission to manage this category");
      }
   }


}