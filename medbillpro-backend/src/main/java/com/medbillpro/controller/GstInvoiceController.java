package com.medbillpro.controller;

import com.medbillpro.apiResponse.ApiResponse;
import com.medbillpro.constants.URLMapping;
import com.medbillpro.entity.Employee;
import com.medbillpro.entity.GstInvoice;
import com.medbillpro.repository.GstInvoiceRepository;
import com.medbillpro.service.GstInvoiceService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping(URLMapping.GENRATE_INVICE)
    public ResponseEntity<byte[]> getGenrateInvoice() {
        ApiResponse apiResponse = new ApiResponse();
        try(InputStream inputStream = new ClassPathResource("reports/test.jrxml").getInputStream()) {
            var jasperReport = JasperCompileManager.compileReport(inputStream);

            Employee emp1 = new Employee("John Smith", "9876543210", "john.smith@example.com", "123 MG Road, Pune"); // Using no-args constructor + setters
            Employee emp2 = new Employee();
            emp2.setName("Priya Sharma");
            emp2.setMobile("9123456780");
            emp2.setEmail("priya.sharma@example.com");
            emp2.setAddress("45 Residency Lane, Mumbai");
            ArrayList empList = new ArrayList<>();
            empList.add(emp1);
            empList.add(emp2);
            JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(empList);
            Map<String, Object> parameters = new HashMap<>();
            JasperPrint jasPdfPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
          byte[] pdf =  JasperExportManager.exportReportToPdf(jasPdfPrint);
          return ResponseEntity.ok()
                  .contentType(MediaType.APPLICATION_PDF)
                  .body(pdf);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
