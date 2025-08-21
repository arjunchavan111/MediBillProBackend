package com.medbillpro.repository;

import com.medbillpro.dto.PurchaseProjection;
import com.medbillpro.dto.PurchaseSummaryDTO;
import com.medbillpro.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Query(value = "SELECT pd.purchase_id AS purchase_id,pd.supplier_name, pd.supplier_address,  \n" +
            "pd.date, pd.mrp, pd.selling_price, pd.total_amount, pd.status,   pd.mobile_number,   pp.id AS productId,  \n" +
            "pp.product_name,pp.quantity, pp.cost_price, pp.selling_price AS pp_selling_price, pp.gst \n" +
            "FROM purchase_details pd LEFT JOIN purchase_product pp ON pd.purchase_id = pp.purchase_id", nativeQuery = true)
    List<PurchaseProjection> getPurchasesAndProduct();
}
