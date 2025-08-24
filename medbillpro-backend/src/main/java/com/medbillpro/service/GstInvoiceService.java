package com.medbillpro.service;



import com.medbillpro.apiResponse.ApiResponse;
import com.medbillpro.entity.GstInvoice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GstInvoiceService {

    GstInvoice getInvoiceById(Long id);

    GstInvoice saveInvoice(GstInvoice gstRequest);

    List<GstInvoice> getAllInvoices();

    GstInvoice updateInvoice(Long id, GstInvoice updatedInvoice);

    void deleteInvoice(Long id);
}
