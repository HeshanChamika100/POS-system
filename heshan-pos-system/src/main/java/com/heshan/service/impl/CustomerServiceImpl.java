package com.heshan.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.heshan.model.Customer;
import com.heshan.repository.CustomerRepository;
import com.heshan.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

   private final CustomerRepository customerRepository;

   @Override
   public Customer createCustomer(Customer customer) {
      return customerRepository.save(customer);
   }

   @Override
   public Customer updateCustomer(Long id, Customer customer) throws Exception {
      Customer customerToUpdate = customerRepository.findById(id)
            .orElseThrow(() -> new Exception("Customer not found"));
      customerToUpdate.setFullName(customer.getFullName());
      customerToUpdate.setEmail(customer.getEmail());
      customerToUpdate.setPhone(customer.getPhone());
      return customerRepository.save(customerToUpdate);
   }

   @Override
   public void deleteCustomer(Long id) throws Exception {
      Customer customer = customerRepository.findById(id).orElseThrow(() -> new Exception("Customer not found"));
      customerRepository.delete(customer);
   }

   @Override
   public Customer getCustomerById(Long id) throws Exception {
      return customerRepository.findById(id).orElseThrow(() -> new Exception("Customer not found"));
   }

   @Override
   public List<Customer> getAllCustomers() throws Exception {
      return customerRepository.findAll();
   }

   @Override
   public List<Customer> searchCustomers(String keyword) throws Exception {
      return customerRepository.findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword);
   }
}