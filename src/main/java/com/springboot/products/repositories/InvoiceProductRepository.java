package com.springboot.products.repositories;

import com.springboot.products.models.InvoiceProduct;
import com.springboot.products.models.InvoiceProductPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct,InvoiceProductPK> {

}
