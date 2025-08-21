package com.medbillpro.service;


import com.medbillpro.entity.Purchase;

import java.util.List;

public interface PurchaseService {
    Purchase savePurchase(Purchase purchase);
    List<Purchase> getAllPurchases();
    void deletePurchasesByIds(List<Long> ids);
}
