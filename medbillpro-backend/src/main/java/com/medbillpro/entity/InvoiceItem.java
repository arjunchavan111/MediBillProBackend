package com.medbillpro.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoice_item")
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hsnCode;
    private String productName;
    private String manufacturer;
    private String unit;
    private int quantity;
    private String schame;
    private String batchNumber;
    private LocalDate expiryDate;
    private double mrp;
    private double rate;
    private double discount;
    private double gstPercent;
    private double gstAmount;
    private double totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    private GstInvoice invoice;
/*    // InvoiceItem.java
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private ProductDetails productDetails;*/


}