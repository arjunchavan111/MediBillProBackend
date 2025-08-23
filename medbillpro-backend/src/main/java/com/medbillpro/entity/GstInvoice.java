// GstInvoice.java
package com.medbillpro.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="gst_invoice")
public class GstInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    // Buyer Info
    private String buyerName;
    private String buyerAddress;
    private String buyerPhone;
    private String buyerStateCode;
    private String buyerGstin;
    private String buyerPan;
    private String buyerDlNo1;
    private String buyerDlNo2;

    // Tax Summary
    private double taxableAmount;
    private double cgstPercent;
    private double cgstAmount;
    private double sgstPercent;
    private double sgstAmount;
    private double totalGst;
    private double grossAmount;
    private double discountAmount;
    private double netAmount;

    // Declaration & Bank Info
    private String declaration;
    private String bankName;
    private String bankAccountNumber;
    private String bankIfscCode;

    private String irnNumber;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceItem> items;

}
