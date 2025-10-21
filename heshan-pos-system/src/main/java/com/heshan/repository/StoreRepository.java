package com.heshan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heshan.model.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

   Store findByStoreAdminId(Long adminId);
}
