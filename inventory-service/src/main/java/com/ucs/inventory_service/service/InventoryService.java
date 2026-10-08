package com.ucs.inventory_service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucs.inventory_service.dto.InventoryResponse;
import com.ucs.inventory_service.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class InventoryService {

    private final InventoryRepository repository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode){
           return repository.findBySkuCodeIn(skuCode).stream()
                            .map(inventory ->{
                                return 
                                InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity()>0)
                                .build();
                            }).toList();
    }

}
