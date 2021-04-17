package com.example.dto.controllers;

import com.example.dto.dto.ProductDto;
import com.example.dto.entities.ProductEntity;
import com.example.dto.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Vadim Dimnich
 */
@RestController
@RequestMapping(value = "/speculations")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

//    @RequestMapping(value = "/simple")
//    public String simple() {
//        return "Легкий GET запрос";
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProductDto> read(@PathVariable("id") Integer id) {
        ResponseEntity<ProductDto> responseEntity;

        try {
            ProductDto productDto = productService.findById(id);
            productService.pack(productDto);
            productService.makeMargin(productDto);
            responseEntity = new ResponseEntity<>(productDto, HttpStatus.OK);
        } catch (NullPointerException ignore) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<ProductDto>> read() {
        List<ProductDto> productDtoList = productService.findAll();
        ResponseEntity<List<ProductDto>> responseEntity;

        if (productDtoList.size() > 0) {
            productService.pack(productDtoList);
            productService.makeMargin(productDtoList);
            responseEntity = new ResponseEntity<>(productDtoList, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ProductEntity productEntity) {
        productService.save(productEntity);
        return new ResponseEntity<>("Запись добавлена: " + productEntity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody ProductEntity productEntity) {
        productService.save(productEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        boolean deleted = productService.delete(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
