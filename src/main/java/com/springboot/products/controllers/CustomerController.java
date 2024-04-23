package com.springboot.products.controllers;

import com.springboot.products.dtos.CustomerRequestDto;
import com.springboot.products.models.Customer;
import com.springboot.products.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping(value = "/customers")
    public ResponseEntity<Page<Customer>> getCustomers(@PageableDefault(size = 5) Pageable pageable){
        Page<Customer> customersPage= customerService.getCustomers(pageable);
        return ResponseEntity.ok(customersPage);
    }

    @GetMapping(value = "/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        Customer customer= customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }


    @GetMapping(value = "/customers-by-full-name-containing/{fullName}")
    public ResponseEntity<Page<Customer>> getCustomersByFullNameContaining(@PathVariable String fullName,
                                                                           @PageableDefault(size = 5) Pageable pageable){
        Page<Customer> customersPage= customerService.getCustomersByFullNameContaining(fullName,pageable);
        return ResponseEntity.ok(customersPage);
    }


    @PostMapping(value = "/customers")
    public ResponseEntity<Customer> addCustomer(@RequestBody @Valid CustomerRequestDto customerRequestDto){
        Customer customer= new Customer();
        BeanUtils.copyProperties(customerRequestDto,customer);

        Customer addedCustomer= customerService.addCustomer(customer);
        return new ResponseEntity<>(addedCustomer,HttpStatus.CREATED);
    }

    @PutMapping(value = "/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody @Valid CustomerRequestDto customerRequestDto,
                                                   @PathVariable Long id){
        Customer customer= new Customer();
        BeanUtils.copyProperties(customerRequestDto,customer);
        customer.setId(id);

        Customer updatedCustomer= customerService.updateCustomer(customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping(value = "/customers/{id}")
    public ResponseEntity<Customer> removeCustomer(@PathVariable Long id){
        Customer customer= customerService.removeCustomer(id);
        return ResponseEntity.ok(customer);
    }


}
