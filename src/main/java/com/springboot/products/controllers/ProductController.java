package com.springboot.products.controllers;

import com.springboot.products.dtos.ProductRequestDto;
import com.springboot.products.models.Product;
import com.springboot.products.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping(value = "/products")
    public ResponseEntity<Page<Product>> getProducts(@PageableDefault(size = 5) Pageable pageable){
        Page<Product> productsPage= productService.getProducts(pageable);
        return ResponseEntity.ok(productsPage);
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product product= productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping(value = "/products-by-price-between")
    public ResponseEntity<Page<Product>> getProductsByPriceBetween(@RequestParam Double minPrice,
                                                                   @RequestParam Double maxPrice,
                                                                   @PageableDefault(size = 5) Pageable pageable){
        Page<Product> productsPage= productService.getProductsByPriceBetween(minPrice,maxPrice,pageable);
        return ResponseEntity.ok(productsPage);
    }


    @GetMapping(value = "/products-by-creation-date-between")
    public ResponseEntity<Page<Product>> getProductsByCreationDateBetween(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime oriDate,
                                                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime finalDate,
                                                                          @PageableDefault(size = 5) Pageable pageable){
        Page<Product> productsPage= productService.getProductsByCreationDateBetween(oriDate,finalDate,pageable);
        return ResponseEntity.ok(productsPage);
    }

    @GetMapping(value = "/products-by-country-and-creation-date-between")
    public ResponseEntity<Page<Product>> getProductsByCountryAndCreationDateBetween(@RequestParam String country,
                                                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime oriDate,
                                                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime finalDate,
                                                                                    @PageableDefault(size = 5) Pageable pageable){
        Page<Product> productsPage= productService.getProductsByCountryAndCreationDateBetween(country,oriDate,finalDate,pageable);
        return ResponseEntity.ok(productsPage);
    }


    @PostMapping(value = "/products")
    public ResponseEntity<Product> addProduct(@RequestBody @Valid ProductRequestDto productRequestDto){
        Product product= new Product();
        BeanUtils.copyProperties(productRequestDto,product);
        product.setCreationDate(LocalDateTime.now(ZoneId.of("UTC-5")));//Local time of Colombia

        Product addedProduct = productService.addProduct(product);
        return new ResponseEntity<>(addedProduct,HttpStatus.CREATED);
    }

    @PutMapping(value = "/products/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid ProductRequestDto productRequestDto,
                                                @PathVariable Long id){
        Product product= new Product();
        BeanUtils.copyProperties(productRequestDto,product);
        product.setId(id);

        Product updatedProduct= productService.updateProduct(product);
        return ResponseEntity.ok(updatedProduct);
    }


}
