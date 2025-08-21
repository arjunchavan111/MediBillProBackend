package com.medbillpro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String productName;
    private Integer quantity;
    private Double costPrice;
    private Double sellingPrice;
    private Double gst;
}
