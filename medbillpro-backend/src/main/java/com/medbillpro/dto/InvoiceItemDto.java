// InvoiceItemDto.java
package com.medbillpro.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InvoiceItemDto {
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
}
