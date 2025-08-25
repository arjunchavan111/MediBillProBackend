package com.medbillpro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product_Details")
public class ProductDetails {
    @Id
    private String productId="PRODUCTID-"+Math.random();

    private String hsnCode;
    private String productName;
    private String manufacturer;
    private String unit;
    private int quantity;
    private String schame;
    private String batchNumber;
    private LocalDate expiryDate;
    private double mrp;
    private double buyingPice;
    private double sellingPice;
    private double discount;
    private double gstPercent;
    private double gstAmount;
    private double totalAmount;

}