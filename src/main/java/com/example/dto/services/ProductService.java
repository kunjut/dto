package com.example.dto.services;

import com.example.dto.dto.ProductDto;
import com.example.dto.entities.ProductEntity;
import com.example.dto.repositories.ProductRepository;
import com.example.dto.utils.MappingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vadim Dimnich
 */

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final MappingUtils mappingUtils;
    private final Integer margin = 5;
    private final String packaging = "Упаковано в лучшем виде";

    public boolean delete(Integer id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception ignore) {
            return false;
        }
    }


    public void saveAll(List<ProductEntity> productEntities) {
        productRepository.saveAll(productEntities);
    }

    public void save(ProductEntity productEntity) {
        productRepository.save(productEntity);
    }

    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    // реализуем методы для поиска одного продукта или списка продуктов,
    // но перед эти мы конвертируем entity в dto с помощь написанного метода.

    public List<ProductDto> findAll() {
        return productRepository.findAll()
                .stream()
                .map(mappingUtils::mapToProductDto)
                .collect(Collectors.toList()
        );
    }

    public ProductDto findById(Integer id) {
        return mappingUtils.mapToProductDto(         // в метод mapToProductDto
                productRepository.findById(id)       // поместили результат поиска по id
                        .orElse(new ProductEntity()) // если ни чего не нашли, то вернем пустой entity
        );
    }

    // упаковываем товар
    public void pack(List<ProductDto> list) {
        list.forEach(productDto ->
                productDto.setPackaging(packaging)
        );
    }

    public void pack(ProductDto productDto) {
        productDto.setPackaging(packaging);
    }

    // делаем наценку
    public void makeMargin(List<ProductDto> list) {
        list.forEach(productDto ->
                productDto.setSalePrice(productDto.getPurchasePrice() + margin));
    }

    public void makeMargin(ProductDto productDto) {
        productDto.setSalePrice(productDto.getPurchasePrice() + margin);
    }

}
