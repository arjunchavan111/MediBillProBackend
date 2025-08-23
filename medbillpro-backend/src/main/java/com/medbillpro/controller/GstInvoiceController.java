package com.medbillpro.controller;

import com.medbillpro.apiResponse.ApiResponse;
import com.medbillpro.dto.GstInvoiceDto;
import com.medbillpro.entity.GstInvoice;
import com.medbillpro.service.GstInvoiceService;
import com.medbillpro.serviceImpl.GstInvoiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class GstInvoiceController {

    @Autowired
    GstInvoiceService gstInvoiceService;

    @Autowired
    GstInvoiceMapper gstInvoiceMapper;

    @PostMapping("/create")
    public ResponseEntity createInvoice(@RequestBody GstInvoiceDto dto) {
        ApiResponse apiResponse =   gstInvoiceService.saveInvoice(dto);
       // gstInvoiceMapper.toEntity(dto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity getInvoiceById(@PathVariable Long id) {
        GstInvoice invoice = gstInvoiceService.getInvoiceById(id);
        return ResponseEntity.ok(invoice);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GstInvoice>> getAllInvoices() {
        List<GstInvoice> invoices = gstInvoiceService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateInvoice(@PathVariable Long id, @RequestBody GstInvoiceDto dto) {
        ApiResponse apiResponse= gstInvoiceMapper.toEntity(dto);
       // GstInvoice updated = gstInvoiceService.updateInvoice(id, gstInvoice);
       // return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        gstInvoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }
}
