package com.heshan.mapper;

import com.heshan.model.Store;
import com.heshan.model.User;
import com.heshan.payload.dto.StoreDTO;

public class StoreMapper {

   public static StoreDTO toDTO(Store store) {
      StoreDTO storeDTO = new StoreDTO();
      storeDTO.setId(store.getId());
      storeDTO.setBrand(store.getBrand());
      storeDTO.setDescription(store.getDescription());
      storeDTO.setStoreAdmin(UserMapper.toDTO(store.getStoreAdmin()));
      storeDTO.setStoreType(store.getStoreType());
      storeDTO.setContact(store.getContact());
      storeDTO.setCreatedAt(store.getCreatedAt());
      storeDTO.setUpdatedAt(store.getUpdatedAt());
      storeDTO.setStatus(store.getStatus());

      return storeDTO;
   }

   public static Store toEntity(StoreDTO storeDTO, User storeAdmin) {
      Store store = new Store();
      store.setBrand(storeDTO.getBrand());
      store.setDescription(storeDTO.getDescription());
      store.setStoreAdmin(storeAdmin);
      store.setStoreType(storeDTO.getStoreType());
      store.setContact(storeDTO.getContact());
      // Don't set createdAt, updatedAt, and status - let @PrePersist handle them
      return store;
   }
}
