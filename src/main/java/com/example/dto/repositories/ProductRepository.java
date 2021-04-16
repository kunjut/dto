package com.example.dto.repositories;

import com.example.dto.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vadim Dimnich
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
