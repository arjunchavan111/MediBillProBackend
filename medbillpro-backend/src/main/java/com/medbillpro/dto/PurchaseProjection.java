package com.medbillpro.dto;

import java.math.BigDecimal;

public interface PurchaseProjection {
    Long getPurchaseId();
    String getSupplierName();
    String getSupplierAddress();
    String getDate();
    BigDecimal getMrp();
    BigDecimal getSellingPrice();
    BigDecimal getTotalAmount();
    String getStatus();
    String getMobileNumber();

    Long getProductId();
    String getProductName();
    Integer getQuantity();
    BigDecimal getCostPrice();
    BigDecimal getProductSellingPrice();
    BigDecimal getGst();
}
