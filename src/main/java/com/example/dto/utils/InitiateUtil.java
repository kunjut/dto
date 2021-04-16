package com.example.dto.utils;

import com.example.dto.dto.ProductDto;
import com.example.dto.entities.ProductEntity;
import com.example.dto.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Vadim Dimnich
 */

@Service
@RequiredArgsConstructor
public class InitiateUtil implements CommandLineRunner {

    private final ProductService productService;

    @Override
    public void run(String... args) throws Exception {

        // инициализируем таблицу с продуктами
        // так написано благодаря @Accessors(chain = true)
        List<ProductEntity> product = new ArrayList<>(
                Arrays.asList(
                        new ProductEntity()
                                .setName("Картофель")
                                .setPurchasePrice(20),
                        new ProductEntity()
                                .setName("Морковь")
                                .setPurchasePrice(14)
                )
        );

        productService.saveAll(product);

        System.out.println("\nТаблица фруктов");
        for (ProductEntity productEntity : productService.getAll()) {
            System.out.println(productEntity);
        }



        System.out.println("\nВитрина магазина");
//        for (ProductDto productDto : productService.findAll()) {
//            System.out.println(productDto);
//        }

        List<ProductDto> productDtos = productService.findAll();

        productService.pack(productDtos);
        productService.makeMargin(productDtos);

        for (ProductDto productDto : productDtos) {
            System.out.println(
                    String.format(
                        "Купите: %s, цена за 1кг: %d", productDto.getName(), productDto.getSalePrice()
            ));
        }
    }
}
