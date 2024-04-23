package com.springboot.products.services;

import com.springboot.products.exceptions.*;
import com.springboot.products.models.Customer;
import com.springboot.products.models.Invoice;
import com.springboot.products.repositories.CustomerRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final EntityManager entityManager;


    @Autowired
    public CustomerService(CustomerRepository customerRepository, EntityManager entityManager) {
        this.customerRepository = customerRepository;
        this.entityManager = entityManager;
    }

    public Page<Customer> getCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id: " + id + " not found!"));
    }

    public Page<Customer> getCustomersByFullNameContaining(String fullName, Pageable pageable) {
        return customerRepository.findByFullNameIgnoreCaseContaining(fullName,pageable);
    }


    @Transactional
    public Customer addCustomer(Customer customer) {
        if (customerRepository.existsByFullNameIgnoreCase(customer.getFullName())) {
            throw new CustomerFullNameExistsException("Customer with Full Name: " + customer.getFullName() + " exists already!");
        }
        if (customerRepository.existsByAddressIgnoreCase(customer.getAddress())) {
            throw new CustomerAddressExistsException("Customer Address: " + customer.getAddress() + " exists already!");
        }
        if (customerRepository.existsByPhoneNumberIgnoreCase(customer.getPhoneNumber())) {
            throw new CustomerPhoneNumberExistsException("Customer with Phone Number: " + customer.getPhoneNumber() + " exists already!");
        }
        return customerRepository.save(customer);
    }

    @Transactional
    public Customer updateCustomer(Customer customer) {
        Customer recoveredCustomer = getCustomerById(customer.getId());

        if(fullNameExistsAndBelongsToAnotherEntity(customer.getFullName(),recoveredCustomer)){
            throw new CustomerFullNameExistsException("Customer with Full Name: " + customer.getFullName() + " exists already!");
        }
        if(addressExistsAndBelongsToAnotherEntity(customer.getAddress(),recoveredCustomer)){
            throw new CustomerAddressExistsException("Customer Address: " + customer.getAddress() + " exists already!");
        }
        if(phoneNumberExistsAndBelongsToAnotherEntity(customer.getPhoneNumber(),recoveredCustomer)){
            throw new CustomerPhoneNumberExistsException("Customer with Phone Number: " + customer.getPhoneNumber() + " exists already!");
        }
        BeanUtils.copyProperties(customer,recoveredCustomer,"invoices");//note: ignore relationship's properties
        return recoveredCustomer;
    }


    private boolean fullNameExistsAndBelongsToAnotherEntity(String fullName,Customer customer) {
        return customerRepository.existsByFullNameIgnoreCase(fullName) && !fullName.equalsIgnoreCase(customer.getFullName());
    }
    private boolean addressExistsAndBelongsToAnotherEntity(String address, Customer customer) {
        return customerRepository.existsByAddressIgnoreCase(address) && !address.equalsIgnoreCase(customer.getAddress());
    }
    private boolean phoneNumberExistsAndBelongsToAnotherEntity(String phoneNumber, Customer customer) {
        return customerRepository.existsByPhoneNumberIgnoreCase(phoneNumber) && !phoneNumber.equalsIgnoreCase(customer.getPhoneNumber());
    }

    @Transactional
    public Customer removeCustomer(Long id) {
        Customer customer= getCustomerById(id);

        //necessary to REMOVE ALL RELATED invoices
        //List<Invoice> invoices= customer.getInvoices();

        //REMOVE ALL RELATED InvoiceProduct FIRST
        //this line can be replaced adding this in the class Invoice, in the relationship property called invoiceProductList: cascade = CascadeType.REMOVE
        /*for(Invoice invoice : invoices) {
            invoice.getInvoiceProductList().forEach(entityManager::remove);
        }*/

        //REMOVE ALL RELATED invoices
        //invoices.forEach(entityManager::remove);//this line can be replaced adding this in the class Customer, in the relationship property called invoices: cascade = CascadeType.REMOVE

        customerRepository.delete(customer);
        return customer;
    }



}
