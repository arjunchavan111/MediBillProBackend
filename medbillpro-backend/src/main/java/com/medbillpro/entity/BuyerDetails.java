package com.medbillpro.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "buyer_details")
@Data
public class BuyerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buyerId;

    private String buyerName;
    private String buyerAddress;
    private String buyerPhone;
    private String buyerStateCode;
    private String buyerGstin;
    private String buyerPan;
    private String buyerDlNo1;
    private String buyerDlNo2;
}
