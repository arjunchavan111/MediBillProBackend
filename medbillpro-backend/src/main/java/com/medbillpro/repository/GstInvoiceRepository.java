// GstInvoiceRepository.java
package com.medbillpro.repository;


import com.medbillpro.entity.GstInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GstInvoiceRepository extends JpaRepository<GstInvoice, Long> {
}
