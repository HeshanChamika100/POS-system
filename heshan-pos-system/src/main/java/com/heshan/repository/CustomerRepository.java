package com.heshan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heshan.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
   List<Customer> findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String fullName, String email);
}
