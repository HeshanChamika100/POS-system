package com.heshan.service;

import java.util.List;

import com.heshan.domain.StoreStatus;
import com.heshan.exceptions.UserException;
import com.heshan.model.Store;
import com.heshan.model.User;
import com.heshan.payload.dto.StoreDTO;

public interface StoreService {

   StoreDTO createStore(StoreDTO storeDTO, User user);
   StoreDTO getStoreById(Long id) throws Exception;
   List<StoreDTO> getAllStores();
   Store getStoreByAdmin() throws UserException;
   StoreDTO updateStore(Long id, StoreDTO storeDTO) throws UserException, Exception;
   void deleteStore(Long id) throws UserException;
   StoreDTO getStoreByEmployee() throws UserException;

   StoreDTO moderateStore(Long id, StoreStatus status) throws Exception;
}
