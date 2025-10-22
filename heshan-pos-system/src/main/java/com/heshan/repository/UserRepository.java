package com.heshan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heshan.model.Store;
import com.heshan.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

   User findByEmail(String email);

   List<User> findByStore(Store store);
   List<User> findByBranchId(Long branchId);
}
