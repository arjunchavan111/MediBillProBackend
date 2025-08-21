package com.medbillpro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseSummaryDTO {
    private Long purchaseId;
    private String supplierName;
    private String supplierAddress;
    private String date;
    private Double mrp;
    private Double sellingPrice;
    private Double totalAmount;
    private String status;
    private String mobileNumber;
    private List<ProductDTO> products;
}


