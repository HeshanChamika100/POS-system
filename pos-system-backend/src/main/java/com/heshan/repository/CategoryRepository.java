package com.heshan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heshan.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

   List<Category> findByStoreId(Long storeId);
}
