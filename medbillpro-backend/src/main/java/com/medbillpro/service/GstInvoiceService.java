package com.medbillpro.service;



import com.medbillpro.apiResponse.ApiResponse;
import com.medbillpro.entity.GstInvoice;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface GstInvoiceService {

	GstInvoice getInvoiceById(String id);

    GstInvoice saveInvoice(GstInvoice gstRequest);

    List<GstInvoice> getAllInvoices();

    GstInvoice updateInvoice(String id, GstInvoice updatedInvoice);
    
    boolean deleteInvoice(String id);
    
    List<GstInvoice> getInvoicesByDate(LocalDate date) ;
}
