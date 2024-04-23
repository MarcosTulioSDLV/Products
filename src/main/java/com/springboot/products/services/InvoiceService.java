package com.springboot.products.services;

import com.springboot.products.dtos.ProductIdXQuantity;
import com.springboot.products.exceptions.CustomerNotFoundException;
import com.springboot.products.exceptions.InvoiceNotFoundException;
import com.springboot.products.exceptions.NotEnoughProductsInStockException;
import com.springboot.products.exceptions.ProductNotFoundException;
import com.springboot.products.models.*;
import com.springboot.products.repositories.CustomerRepository;
import com.springboot.products.repositories.InvoiceProductRepository;
import com.springboot.products.repositories.InvoiceRepository;
import com.springboot.products.repositories.ProductRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final InvoiceProductRepository invoiceProductRepository;
    private final EntityManager entityManager;


    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, CustomerRepository customerRepository, ProductRepository productRepository, InvoiceProductRepository invoiceProductRepository, EntityManager entityManager) {
        this.invoiceRepository = invoiceRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.invoiceProductRepository = invoiceProductRepository;
        this.entityManager = entityManager;
    }


    public Page<Invoice> getInvoices(Pageable pageable) {
        return invoiceRepository.findAll(pageable);
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(()-> new InvoiceNotFoundException("Invoice with id: "+id+" not found!"));
    }

    @Transactional
    public Invoice addInvoice(Long customerId,Invoice invoice,List<ProductIdXQuantity> productIdXQuantityList) {
        Customer customer= customerRepository.findById(customerId)
                .orElseThrow(()-> new CustomerNotFoundException("Customer with id: "+customerId+" not found!"));

        List<Double> productTotalPrices= new ArrayList<>();
        List<Product> products= new ArrayList<>();
        for(var productIdXQuantity: productIdXQuantityList){
            Long productId= productIdXQuantity.getProductId();
            Integer quantity= productIdXQuantity.getQuantity();
            Product product= productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("Product with id: "+productId+" not found!"));

            if(quantity>product.getStockQuantity()){
                throw new NotEnoughProductsInStockException("There are not enough Products: "+product+ " in Stock!");
            }
            products.add(product);
            productTotalPrices.add(product.getPrice() * quantity);
        }

        //Set and Add Invoice
        Double purchaseTotalPrice= productTotalPrices.stream().reduce(0.0,Double::sum);
        invoice.setPurchaseTotalPrice(purchaseTotalPrice);
        invoice.setCustomer(customer);
        invoiceRepository.save(invoice);

        //Set and Add InvoiceProduct elements
        for(int i=0;i<products.size();i++){
            Product product= products.get(i);
            Double productTotalPrice= productTotalPrices.get(i);
            int quantity= (int)(productTotalPrice/product.getPrice());

            InvoiceProduct invoiceProduct= new InvoiceProduct(new InvoiceProductPK(),invoice,product,quantity,productTotalPrice);
            invoiceProductRepository.save(invoiceProduct);

            product.setStockQuantity(product.getStockQuantity()-quantity);
        }

        return invoice;
    }


    @Transactional
    public Invoice updateInvoice(Invoice invoice) {
        Invoice recoveredInvoice= getInvoiceById(invoice.getId());
        BeanUtils.copyProperties(invoice,recoveredInvoice,"paymentDate","purchaseTotalPrice","customer","invoiceProductList");//note: ignore relationship's properties
        return recoveredInvoice;
    }

    /*@Transactional
    public Invoice removeInvoice(Long id) {
        Invoice invoice= getInvoiceById(id);

        //this line can be replaced adding this in the class Invoice, in the relationship property called invoiceProductList: cascade = CascadeType.REMOVE
        //invoice.getInvoiceProductList().forEach(entityManager::remove);

        invoiceRepository.delete(invoice);
        return invoice;
    }*/



}
