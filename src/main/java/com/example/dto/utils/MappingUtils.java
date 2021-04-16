package com.example.dto.utils;

import com.example.dto.dto.ProductDto;
import com.example.dto.entities.ProductEntity;
import org.springframework.stereotype.Service;

/**
 * @author Vadim Dimnich
 */
@Service
public class MappingUtils {
    // из entity в dto
    public ProductDto mapToProductDto(ProductEntity entity) {
        ProductDto dto = new ProductDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPurchasePrice(entity.getPurchasePrice());
        return dto;
    }
    // из dto в entity
    public ProductEntity mapToProductEntity(ProductDto dto) {
        ProductEntity entity = new ProductEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPurchasePrice(dto.getPurchasePrice());
        return entity;
    }
}
