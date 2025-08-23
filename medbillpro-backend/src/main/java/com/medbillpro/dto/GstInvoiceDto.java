package com.medbillpro.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Data
public class GstInvoiceDto {

    private String sellerName;
    private String sellerAddress;
    private String sellerPhone;
    private String sellerStateCode;
    private String sellerGstin;
    private String sellerPan;
    private String sellerDlNo1;
    private String sellerDlNo2;
    private String sellerFoodLic;

    private String invoiceType;
    private String paymentMode;
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private LocalDate dueDate;
    private String purchaseOrderNumber;
    private String salesman;

    private String buyerName;
    private String buyerAddress;
    private String buyerPhone;
    private String buyerStateCode;
    private String buyerGstin;
    private String buyerPan;
    private String buyerDlNo1;
    private String buyerDlNo2;

    private List<InvoiceItemDto> items;

    private double taxableAmount;
    private double cgstPercent;
    private double cgstAmount;
    private double sgstPercent;
    private double sgstAmount;
    private double totalGst;
    private double grossAmount;
    private double discountAmount;
    private double netAmount;

    private String declaration;
    private String bankName;
    private String bankAccountNumber;
    private String bankIfscCode;

    private String irnNumber;
}