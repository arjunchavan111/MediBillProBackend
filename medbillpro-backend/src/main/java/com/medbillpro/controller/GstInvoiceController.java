package com.medbillpro.controller;

import com.medbillpro.apiResponse.ApiResponse;
import com.medbillpro.constants.URLMapping;
import com.medbillpro.entity.GstInvoice;
import com.medbillpro.repository.GstInvoiceRepository;
import com.medbillpro.service.GstInvoiceService;
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


    @PostMapping(URLMapping.CREATE_INVOICE)
    public ResponseEntity<ApiResponse> createInvoice(@RequestBody GstInvoice gstRequest) {
        ApiResponse apiResponse = new ApiResponse();

        try {
            GstInvoice response = gstInvoiceService.saveInvoice(gstRequest);

            if (response != null) {
                apiResponse.setMessage("Invoice saved successfully");
                apiResponse.setStatus(1);
                apiResponse.setSuccess(true);
                return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
            } else {
                apiResponse.setErrorMessage("Invoice not saved");
                apiResponse.setStatus(0);
                apiResponse.setData(null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
            }

        } catch (Exception e) {
            apiResponse.setErrorMessage("Error saving invoice: " + e.getMessage());
            apiResponse.setStatus(0);
            apiResponse.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    @GetMapping(URLMapping.GET_INVOICE_BY_ID)
    public ResponseEntity<ApiResponse> getInvoiceById(@PathVariable String id) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            GstInvoice invoice = gstInvoiceService.getInvoiceById(id);

            if (invoice != null) {
                apiResponse.setMessage("Invoice fetched successfully");
                apiResponse.setStatus(1);
                apiResponse.setSuccess(true);
                apiResponse.setData(invoice);
                return ResponseEntity.ok(apiResponse);
            } else {
                apiResponse.setErrorMessage("Invoice not found with ID: " + id);
                apiResponse.setStatus(0);
                apiResponse.setData(null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
            }
        } catch (Exception e) {
            apiResponse.setErrorMessage("Error fetching invoice: " + e.getMessage());
            apiResponse.setStatus(0);
            apiResponse.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }


    @GetMapping(URLMapping.GET_ALL_INVOICE)
    public ResponseEntity<ApiResponse> getAllInvoices() {
        ApiResponse apiResponse = new ApiResponse();
        try {
            List<GstInvoice> invoices = gstInvoiceService.getAllInvoices();

            if (invoices != null && !invoices.isEmpty()) {
                apiResponse.setMessage("Invoices fetched successfully");
                apiResponse.setStatus(1);
                apiResponse.setSuccess(true);
                apiResponse.setData(invoices);
                return ResponseEntity.ok(apiResponse);
            } else {
                apiResponse.setErrorMessage("No invoices found");
                apiResponse.setStatus(0);
                apiResponse.setData(null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
            }
        } catch (Exception e) {
            apiResponse.setErrorMessage("Error while fetching invoices: " + e.getMessage());
            apiResponse.setStatus(0);
            apiResponse.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }


    @PutMapping(URLMapping.UPDATE_INVOICE)
    public ResponseEntity<ApiResponse> updateInvoice(@PathVariable String id, @RequestBody GstInvoice updateReq) {
        ApiResponse apiResponse = new ApiResponse();

        try {
            GstInvoice updated = gstInvoiceService.updateInvoice(id, updateReq);

            if (updated != null) {
                apiResponse.setMessage("Invoice updated successfully");
                apiResponse.setStatus(1);
                apiResponse.setSuccess(true);
                apiResponse.setData(updated);
                return ResponseEntity.ok(apiResponse);
            } else {
                apiResponse.setErrorMessage("Invoice not found with ID: " + id);
                apiResponse.setStatus(0);
                apiResponse.setData(null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
            }

        } catch (Exception e) {
            apiResponse.setErrorMessage("Error updating invoice: " + e.getMessage());
            apiResponse.setStatus(0);
            apiResponse.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    @DeleteMapping(URLMapping.DELETE_INVOICE)
    public ResponseEntity<ApiResponse> deleteInvoice(@PathVariable String id) {
        ApiResponse apiResponse = new ApiResponse();

        try {
            boolean deleted = gstInvoiceService.deleteInvoice(id); // Assuming this returns a boolean

            if (deleted) {
                apiResponse.setMessage("Invoice deleted successfully");
                apiResponse.setStatus(1);
                apiResponse.setSuccess(true);
                apiResponse.setData(null);
                return ResponseEntity.ok(apiResponse);
            } else {
                apiResponse.setErrorMessage("Invoice not found with ID: " + id);
                apiResponse.setStatus(0);
                apiResponse.setData(null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
            }

        } catch (Exception e) {
            apiResponse.setErrorMessage("Error deleting invoice: " + e.getMessage());
            apiResponse.setStatus(0);
            apiResponse.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }
}
