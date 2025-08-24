// GstInvoiceRepository.java
package com.medbillpro.repository;


import com.medbillpro.entity.GstInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GstInvoiceRepository extends JpaRepository<GstInvoice, Long> {

    @Query("SELECT DISTINCT i FROM GstInvoice i " +
            "LEFT JOIN FETCH i.items it ")
    List<GstInvoice> findAllWithItemsAndProducts();
}
