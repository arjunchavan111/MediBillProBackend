package com.medbillpro.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Purchase_Details")
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseId;

    private String supplierName;
    private String supplierAddress;
    private String date;
    private Double mrp;
    private Double sellingPrice;
    private Double totalAmount;
    private String status;
    private String mobileNumber;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    private List<PurchaseProduct> products;
}
