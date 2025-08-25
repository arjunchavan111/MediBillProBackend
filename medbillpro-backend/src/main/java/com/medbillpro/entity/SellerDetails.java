package com.medbillpro.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "seller_details")
@Data
public class SellerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;

    private String sellerName;
    private String sellerAddress;
    private String sellerPhone;
    private String sellerStateCode;
    private String sellerGstin;
    private String sellerPan;
    private String sellerDlNo1;
    private String sellerDlNo2;
    private String sellerFoodLic;
}
