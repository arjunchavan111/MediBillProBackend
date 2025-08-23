package com.medbillpro.service;



import com.medbillpro.apiResponse.ApiResponse;
import com.medbillpro.dto.GstInvoiceDto;
import com.medbillpro.entity.GstInvoice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GstInvoiceService {

    ApiResponse saveInvoice(GstInvoiceDto dto);

    GstInvoice getInvoiceById(Long id);

    List<GstInvoice> getAllInvoices();

    GstInvoice updateInvoice(Long id, GstInvoice updatedInvoice);

    void deleteInvoice(Long id);
}
