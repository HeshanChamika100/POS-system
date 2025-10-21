package com.heshan.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.heshan.domain.StoreStatus;
import com.heshan.exceptions.UserException;
import com.heshan.mapper.StoreMapper;
import com.heshan.model.Store;
import com.heshan.model.StoreContact;
import com.heshan.model.User;
import com.heshan.payload.dto.StoreDTO;
import com.heshan.repository.StoreRepository;
import com.heshan.service.StoreService;
import com.heshan.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

   private final StoreRepository storeRepository;
   private final UserService userService;

   @Override
   public StoreDTO createStore(StoreDTO storeDTO, User user) {
      Store store = StoreMapper.toEntity(storeDTO, user);

      return StoreMapper.toDTO(storeRepository.save(store));
   }

   @Override
   public StoreDTO getStoreById(Long id) throws Exception {

      Store store = storeRepository.findById(id)
            .orElseThrow(() -> new Exception("Store not found with id: " + id));

      return StoreMapper.toDTO(store);
   }

   @Override
   public List<StoreDTO> getAllStores() {
      List<Store> dtos = storeRepository.findAll();
      return dtos.stream().map(StoreMapper::toDTO).collect(Collectors.toList());
   }

   @Override
   public Store getStoreByAdmin() throws UserException {
      User admin = userService.getCurrentUser();
      return storeRepository.findByStoreAdminId(admin.getId());
   }

   @Override
   public StoreDTO updateStore(Long id, StoreDTO storeDTO) throws Exception {
      User currentUser = userService.getCurrentUser();
      Store existingStore = storeRepository.findByStoreAdminId(currentUser.getId());

      if (existingStore == null) {
         throw new Exception("Store not found.");
      }

      existingStore.setBrand(storeDTO.getBrand());
      existingStore.setDescription(storeDTO.getDescription());

      if (storeDTO.getStoreType() != null) {
         existingStore.setStoreType(storeDTO.getStoreType());
      }

      if (storeDTO.getContact() != null) {
         StoreContact contact = StoreContact.builder().address(storeDTO.getContact().getAddress())
               .phone(storeDTO.getContact().getPhone()).email(storeDTO.getContact().getEmail()).build();
         existingStore.setContact(contact);
      }
      Store updatedStore = storeRepository.save(existingStore);
      return StoreMapper.toDTO(updatedStore);
   }

   @Override
   public void deleteStore(Long id) throws UserException {
      Store store = getStoreByAdmin();

      storeRepository.delete(store);
   }

   @Override
   public StoreDTO getStoreByEmployee() throws UserException {
      User currentUser = userService.getCurrentUser();

      if (currentUser == null) {
         throw new UserException("You don't have permission to access this store.");
      }
      return StoreMapper.toDTO(currentUser.getStore());
   }

   @Override
   public StoreDTO moderateStore(Long id, StoreStatus status) throws Exception {
      Store store = storeRepository.findById(id).orElseThrow(() -> new Exception("Store not found with id: " + id));
      store.setStatus(status);
      Store updatedStore = storeRepository.save(store);
      return StoreMapper.toDTO(updatedStore);
   }

}
