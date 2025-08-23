package com.medbillpro.serviceImpl;

import com.medbillpro.apiResponse.ApiResponse;
import com.medbillpro.dto.GstInvoiceDto;
import com.medbillpro.entity.GstInvoice;
import com.medbillpro.repository.GstInvoiceRepository;
import com.medbillpro.service.GstInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GstInvoiceServiceImpl implements GstInvoiceService {

    @Autowired
    GstInvoiceRepository invoiceRepository;
    @Autowired
    GstInvoiceMapper gstInvoiceMapper;
    @Override
    public ApiResponse saveInvoice(GstInvoiceDto dto) {

        ApiResponse gstDto =  gstInvoiceMapper.toEntity(dto);

        return gstDto;
    }

    @Override
    public GstInvoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    @Override
    public List<GstInvoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public GstInvoice updateInvoice(Long id, GstInvoice updatedInvoice) {
        Optional<GstInvoice> existingOpt = invoiceRepository.findById(id);
        if (existingOpt.isPresent()) {
            updatedInvoice.setId(existingOpt.get().getId());
            if (updatedInvoice.getItems() != null) {
                updatedInvoice.getItems().forEach(item -> item.setInvoice(updatedInvoice));
            }
            return invoiceRepository.save(updatedInvoice);
        }
        return null;
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }
}