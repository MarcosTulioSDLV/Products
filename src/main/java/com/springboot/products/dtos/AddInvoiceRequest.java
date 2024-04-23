package com.springboot.products.dtos;

import com.springboot.products.models.Invoice;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString
public class AddInvoiceRequest {

    private Invoice invoice;
    private List<ProductIdXQuantity> productXQuantityList;

    public AddInvoiceRequest(){
    }

    public AddInvoiceRequest(InvoiceRequestDto invoiceRequestDto,List<ProductIdXQuantityRequestDto> productIdXQuantityRequestDtoList){
        invoice= new Invoice();
        BeanUtils.copyProperties(invoiceRequestDto,invoice);

        productXQuantityList= new ArrayList<>();
        for(var productIdXQuantityRequestDto : productIdXQuantityRequestDtoList){
            var productIdXQuantity= new ProductIdXQuantity();
            BeanUtils.copyProperties(productIdXQuantityRequestDto,productIdXQuantity);
            productXQuantityList.add(productIdXQuantity);
        }
    }

}
