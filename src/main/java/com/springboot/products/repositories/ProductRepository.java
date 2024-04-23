package com.springboot.products.repositories;

import com.springboot.products.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    boolean existsByProductNumber(String productNumber);
    boolean existsByNameIgnoreCase(String name);
    Page<Product> getByPriceBetween(Double minPrice, Double maxPrice,Pageable pageable);
    Page<Product> getByCreationDateBetween(LocalDateTime oriDate, LocalDateTime finalDate, Pageable pageable);
    Page<Product> findByCountryIgnoreCaseAndCreationDateBetween(String country, LocalDateTime oriDate, LocalDateTime finalDate, Pageable pageable);


    //Optional<Product> findByProductNumber(String productNumber);
}
