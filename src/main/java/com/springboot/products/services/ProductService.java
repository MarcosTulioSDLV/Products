package com.springboot.products.services;

import com.springboot.products.exceptions.ProductNameExistsException;
import com.springboot.products.exceptions.ProductNotFoundException;
import com.springboot.products.exceptions.ProductNumberExistsException;
import com.springboot.products.models.Product;
import com.springboot.products.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ProductService {

    private final ProductRepository productRepository;


    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product with id: "+id+" not found!"));
    }

    public Page<Product> getProductsByPriceBetween(Double minPrice,Double maxPrice,Pageable pageable) {
        return productRepository.getByPriceBetween(minPrice,maxPrice,pageable);
    }

    public Page<Product> getProductsByCreationDateBetween(LocalDateTime oriDate,LocalDateTime finalDate,Pageable pageable) {
        return productRepository.getByCreationDateBetween(oriDate,finalDate,pageable);
    }

    public Page<Product> getProductsByCountryAndCreationDateBetween(String country,LocalDateTime oriDate,LocalDateTime finalDate,Pageable pageable) {
        return productRepository.findByCountryIgnoreCaseAndCreationDateBetween(country,oriDate,finalDate,pageable);
    }


    @Transactional
    public Product addProduct(Product product){
        if(productRepository.existsByProductNumber(product.getProductNumber())){
            throw new ProductNumberExistsException("Product Number: "+product.getProductNumber()+" already exists!");
        }
        if(productRepository.existsByNameIgnoreCase(product.getName())){
            throw new ProductNameExistsException("Product Name: "+product.getName()+" already exists!");
        }
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Product product) {
        Product recoveredProduct= getProductById(product.getId());

        if(productNumberExistsAndBelongsToAnotherEntity(product.getProductNumber(),recoveredProduct)){
            throw new ProductNumberExistsException("Product Number: "+product.getProductNumber()+" already exists!");
        }
        if(productNameExistsAndBelongsToAnotherEntity(product.getName(),recoveredProduct)){
            throw new ProductNameExistsException("Product Name: "+product.getName()+" already exists!");
        }

        BeanUtils.copyProperties(product,recoveredProduct,"creationDate","invoiceProductList");//note: ignore relationship's properties
        return recoveredProduct;
    }

    private boolean productNumberExistsAndBelongsToAnotherEntity(String productNumber,Product product) {
        return productRepository.existsByProductNumber(productNumber) && !productNumber.equals(product.getProductNumber());
    }

    /*private boolean productNumberExistsAndBelongsToAnotherEntity(String productNumber, Product recoveredProduct) {
     return productRepository.existsByProductNumber(productNumber) && !productRepository.findByProductNumber(productNumber).get().equals(recoveredProduct);
     }*/

    private boolean productNameExistsAndBelongsToAnotherEntity(String name,Product product) {
        return productRepository.existsByNameIgnoreCase(name) && !name.equalsIgnoreCase(product.getName());
    }



}
