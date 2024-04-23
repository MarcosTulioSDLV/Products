package com.springboot.products.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_CUSTOMER")
@AllArgsConstructor
@Getter @Setter @ToString(exclude = "invoices")
@EqualsAndHashCode
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String fullName;

    @Column(nullable = false,unique = true)
    private String address;

    @Column(nullable = false,unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String city;

    @JsonIgnore
    @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE)
    private List<Invoice> invoices= new ArrayList<>();
    

    public Customer(){
    }

}
