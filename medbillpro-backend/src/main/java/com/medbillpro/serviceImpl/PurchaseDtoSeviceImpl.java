package com.medbillpro.serviceImpl;

import com.medbillpro.dto.PurchaseDTO;
import com.medbillpro.entity.Product;
import com.medbillpro.entity.Purchase;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;
@Service
public class PurchaseDtoSeviceImpl {

    /*public PurchaseDTO convertToDTO(Purchase purchase) {
        PurchaseDTO dto = new PurchaseDTO();
        dto.setPurchaseId(purchase.getPurchaseId());
        dto.setSupplierName(purchase.getSupplierName());
        dto.setSupplierAddress(purchase.getSupplierAddress());
        dto.setDate(purchase.getDate());
        dto.setMrp(purchase.getMrp());
        dto.setSellingPrice(purchase.getSellingPrice());
        dto.setTotalAmount(purchase.getTotalAmount());
        dto.setStatus(purchase.getStatus());
        dto.setMobileNumber(purchase.getMobileNumber());

        // If products exist, map them
        if (purchase.getProducts() != null) {
            List<Product> productDTOs = purchase.getProducts().stream()
                    .map(product -> new Product(
                            product.getId(),
                            product.getProductName(),
                            product.getQuantity(),
                            product.getCostPrice(),
                            product.getSellingPrice(),
                            product.getGst()
                    ))
                    .collect(Collectors.toList());
            dto.setProducts(productDTOs);
        }

        return dto;
    }*/
}
