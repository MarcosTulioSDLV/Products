package com.springboot.products.repositories;

import com.springboot.products.models.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    boolean existsByFullNameIgnoreCase(String fullName);
    boolean existsByAddressIgnoreCase(String address);
    boolean existsByPhoneNumberIgnoreCase(String phoneNumber);

    Page<Customer> findByFullNameIgnoreCaseContaining(String fullName,Pageable pageable);
}
