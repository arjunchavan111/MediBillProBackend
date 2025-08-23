package com.medbillpro.serviceImpl;

import com.medbillpro.apiResponse.ApiResponse;
import com.medbillpro.repository.GstInvoiceRepository;
import lombok.*;
import com.medbillpro.dto.GstInvoiceDto;
import com.medbillpro.dto.InvoiceItemDto;
import com.medbillpro.entity.GstInvoice;
import com.medbillpro.entity.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class GstInvoiceMapper {
    @Autowired
    GstInvoiceRepository invoiceRepository;
    public ApiResponse toEntity(GstInvoiceDto dto) {

        ApiResponse apiResponse = new ApiResponse();
        GstInvoice invoice = new GstInvoice();
        invoice.setSellerName(dto.getSellerName());
        invoice.setSellerAddress(dto.getSellerAddress());
        invoice.setSellerPhone(dto.getSellerPhone());
        invoice.setSellerStateCode(dto.getSellerStateCode());
        invoice.setSellerGstin(dto.getSellerGstin());
        invoice.setSellerPan(dto.getSellerPan());
        invoice.setSellerDlNo1(dto.getSellerDlNo1());
        invoice.setSellerDlNo2(dto.getSellerDlNo2());
        invoice.setSellerFoodLic(dto.getSellerFoodLic());

        invoice.setInvoiceType(dto.getInvoiceType());
        invoice.setPaymentMode(dto.getPaymentMode());
        invoice.setInvoiceNumber(dto.getInvoiceNumber());
        invoice.setInvoiceDate(dto.getInvoiceDate());
        invoice.setDueDate(dto.getDueDate());
        invoice.setPurchaseOrderNumber(dto.getPurchaseOrderNumber());
        invoice.setSalesman(dto.getSalesman());

        invoice.setBuyerName(dto.getBuyerName());
        invoice.setBuyerAddress(dto.getBuyerAddress());
        invoice.setBuyerPhone(dto.getBuyerPhone());
        invoice.setBuyerStateCode(dto.getBuyerStateCode());
        invoice.setBuyerGstin(dto.getBuyerGstin());
        invoice.setBuyerPan(dto.getBuyerPan());
        invoice.setBuyerDlNo1(dto.getBuyerDlNo1());
        invoice.setBuyerDlNo2(dto.getBuyerDlNo2());

        invoice.setTaxableAmount(dto.getTaxableAmount());
        invoice.setCgstPercent(dto.getCgstPercent());
        invoice.setCgstAmount(dto.getCgstAmount());
        invoice.setSgstPercent(dto.getSgstPercent());
        invoice.setSgstAmount(dto.getSgstAmount());
        invoice.setTotalGst(dto.getTotalGst());
        invoice.setGrossAmount(dto.getGrossAmount());
        invoice.setDiscountAmount(dto.getDiscountAmount());
        invoice.setNetAmount(dto.getNetAmount());

        invoice.setDeclaration(dto.getDeclaration());
        invoice.setBankName(dto.getBankName());
        invoice.setBankAccountNumber(dto.getBankAccountNumber());
        invoice.setBankIfscCode(dto.getBankIfscCode());
        invoice.setIrnNumber(dto.getIrnNumber());

        //gstInvoiceRepository.save(invoice);

        List<InvoiceItem> items = dto.getItems().stream().map(itemDto -> {
            InvoiceItem item = new InvoiceItem();
            item.setHsnCode(itemDto.getHsnCode());
            item.setProductName(itemDto.getProductName());
            item.setManufacturer(itemDto.getManufacturer());
            item.setUnit(itemDto.getUnit());
            item.setQuantity(itemDto.getQuantity());
            item.setSchame(itemDto.getSchame());
            item.setBatchNumber(itemDto.getBatchNumber());
            item.setExpiryDate(itemDto.getExpiryDate());
            item.setMrp(itemDto.getMrp());
            item.setRate(itemDto.getRate());
            item.setDiscount(itemDto.getDiscount());
            item.setGstPercent(itemDto.getGstPercent());
            item.setGstAmount(itemDto.getGstAmount());
            item.setTotalAmount(itemDto.getTotalAmount());
            item.setInvoice(invoice);
            return item;
        }).collect(Collectors.toList());
        invoice.setItems(items);
        GstInvoice gstInvoice = invoiceRepository.save(invoice);
        if (gstInvoice != null) {
            apiResponse.setData(gstInvoice);
            apiResponse.setMessage("Invoice created successfully");
            apiResponse.setSuccess(true);
            apiResponse.setStatus(1);
        } else {
            apiResponse.setMessage("getting issue for Invoice");
            apiResponse.setSuccess(false);
            apiResponse.setStatus(0);
        }
        return apiResponse;
    }
}