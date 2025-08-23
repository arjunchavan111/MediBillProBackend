package com.medbillpro.service;



import com.medbillpro.entity.GstInvoice;
import com.medbillpro.entity.InvoiceItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InvoiceItemService {

    List<InvoiceItem> saveInvoiceItem(InvoiceItem invoice);

    /*GstInvoice getInvoiceById(Long id);

    List<GstInvoice> getAllInvoices();

    GstInvoice updateInvoice(Long id, GstInvoice updatedInvoice);

    void deleteInvoice(Long id);*/
}
