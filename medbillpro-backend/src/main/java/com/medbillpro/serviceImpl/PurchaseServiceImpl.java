package com.medbillpro.serviceImpl;

import com.medbillpro.apiResponse.ApiResponse;
import com.medbillpro.dto.ProductDTO;
import com.medbillpro.dto.PurchaseDTO;
import com.medbillpro.dto.PurchaseProjection;
import com.medbillpro.entity.Product;
import com.medbillpro.entity.Purchase;
import com.medbillpro.entity.PurchaseProduct;
import com.medbillpro.repository.ProductRepository;
import com.medbillpro.repository.PurchaseRepository;
import com.medbillpro.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Purchase savePurchase(Purchase purchase) {
        try {
            for (PurchaseProduct product : purchase.getProducts()) {
                product.setPurchase(purchase);
            }
            Purchase savedPurchase = purchaseRepository.save(purchase);

            for (PurchaseProduct pp : savedPurchase.getProducts()) {
                Product p = new Product();
                p.setName(pp.getProductName());
                p.setQuantity(pp.getQuantity());
                p.setCostPrice(pp.getCostPrice());
                p.setSellingPrice(pp.getSellingPrice());
                p.setGst(pp.getGst());
                productRepository.save(p);
            }
            return savedPurchase;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }


    public List<PurchaseDTO> getAllPurchasesNested() {
        ApiResponse apiResponse = new ApiResponse<>();
        List<PurchaseProjection> rows = purchaseRepository.getPurchasesAndProduct();
        System.out.println("Rows fetched: " + rows);
        Map<Long, PurchaseDTO> map = new LinkedHashMap<>();

        for (PurchaseProjection r : rows) {
            System.out.println("Rows fetched: " + r.getPurchaseId());
            Long purchaseId = r.getPurchaseId();
            if (purchaseId == null) continue;

            PurchaseDTO dto = map.get(purchaseId);
            if (dto == null) {
                dto = PurchaseDTO.builder()
                        .purchaseId(purchaseId)
                        .supplierName(r.getSupplierName())
                        .supplierAddress(r.getSupplierAddress())
                        .date(r.getDate())
                        .mrp(bdToDouble(r.getMrp()))
                        .sellingPrice(bdToDouble(r.getSellingPrice()))
                        .totalAmount(bdToDouble(r.getTotalAmount()))
                        .status(r.getStatus())
                        .mobileNumber(r.getMobileNumber())
                        .products(new ArrayList<>())
                        .build();
                map.put(purchaseId, dto);
            }
            if (r.getProductId() != null) {
                ProductDTO product = new ProductDTO(
                        r.getProductId(),
                        r.getProductName(),
                        r.getQuantity(),
                        bdToDouble(r.getCostPrice()),
                        bdToDouble(r.getProductSellingPrice()),
                        bdToDouble(r.getGst())
                );
                dto.getProducts().add(product);
            }
        }

        return new ArrayList<>(map.values());
    }

    private Double bdToDouble(BigDecimal bd) {
        return bd == null ? null : bd.doubleValue();
    }

    @Override
    public void deletePurchasesByIds(List<Long> ids) {
        purchaseRepository.deleteAllById(ids);
    }
}
