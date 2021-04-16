package com.example.dto.dto;

import lombok.Data;

/**
 * @author Vadim Dimnich
 */
@Data
public class ProductDto {
    Integer id;
    String name;
    Integer purchasePrice;
    String packaging; // упаковка
    Integer salePrice; // цена реализации
}
