package com.medbillpro.serviceImpl;

import com.medbillpro.apiResponse.ApiResponse;

import com.medbillpro.entity.BuyerDetails;
import com.medbillpro.entity.GstInvoice;
import com.medbillpro.entity.ProductDetails;
import com.medbillpro.entity.SellerDetails;
import com.medbillpro.repository.BuyerDetailsRepository;
import com.medbillpro.repository.GstInvoiceRepository;
import com.medbillpro.repository.ProductDetailsRepository;
import com.medbillpro.repository.SellerDetailsRepository;
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
    ProductDetailsRepository productDetailsRepository;

    @Autowired
    SellerDetailsRepository sellerDetailsRepository;

    @Autowired
    BuyerDetailsRepository buyerDetailsRepository;

    @Override
    public GstInvoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

/*    @Override
    public GstInvoice saveInvoice(GstInvoice gstRequest) {
        return invoiceRepository.save(gstRequest);
    }*/

    @Override
    public List<GstInvoice> getAllInvoices() {
        return invoiceRepository.findAllWithItemsAndProducts();
    }

    @Override
    public GstInvoice updateInvoice(Long id, GstInvoice updatedInvoice) {
        Optional<GstInvoice> existingOpt = invoiceRepository.findById(id);
        if (existingOpt.isPresent()) {
            updatedInvoice.setInvoiceid(existingOpt.get().getInvoiceid());
            if (updatedInvoice.getItems() != null) {
                updatedInvoice.getItems().forEach(item -> item.setInvoice(updatedInvoice));
            }
            return invoiceRepository.save(updatedInvoice);
        }
        return null;
    }
    public GstInvoice saveInvoice(GstInvoice gstRequest) {

        if (gstRequest.getItems() != null) {
            gstRequest.getItems().forEach(item -> {
                item.setInvoice(gstRequest);

                String productName = item.getProductName();
                ProductDetails existing = productDetailsRepository.findByProductName(productName);

                if (existing == null) {
                    ProductDetails newProduct = new ProductDetails();
                    newProduct.setProductName(item.getProductName());
                    newProduct.setHsnCode(item.getHsnCode());
                    newProduct.setManufacturer(item.getManufacturer());
                    newProduct.setUnit(item.getUnit());
                    newProduct.setQuantity(item.getQuantity());
                    newProduct.setSchame(item.getSchame());
                    newProduct.setBatchNumber(item.getBatchNumber());
                    newProduct.setExpiryDate(item.getExpiryDate());
                    newProduct.setMrp(item.getMrp());
                    newProduct.setBuyingPice(item.getRate()); // set buying price
                    newProduct.setSellingPice(item.getRate()); // can update later
                    newProduct.setDiscount(item.getDiscount());
                    newProduct.setGstPercent(item.getGstPercent());
                    newProduct.setGstAmount(item.getGstAmount());
                    newProduct.setTotalAmount(item.getTotalAmount());

                    productDetailsRepository.save(newProduct);
                }
            });
        }
        SellerDetails sellerExisting = sellerDetailsRepository.findBySellerName(gstRequest.getSellerName());
        System.out.println("sellerExisting "+sellerExisting);

        if (sellerExisting == null) {
            SellerDetails saveSellerDetails = new SellerDetails();
            saveSellerDetails.setSellerName(gstRequest.getSellerName());
            saveSellerDetails.setSellerAddress(gstRequest.getSellerAddress());
            saveSellerDetails.setSellerPhone(gstRequest.getSellerPhone());
            saveSellerDetails.setSellerStateCode(gstRequest.getSellerStateCode());
            saveSellerDetails.setSellerGstin(gstRequest.getSellerGstin());
            saveSellerDetails.setSellerPan(gstRequest.getSellerPan());
            saveSellerDetails.setSellerDlNo1(gstRequest.getSellerDlNo1());
            saveSellerDetails.setSellerDlNo2(gstRequest.getSellerDlNo2());
            saveSellerDetails.setSellerFoodLic(gstRequest.getSellerFoodLic());
            sellerDetailsRepository.save(saveSellerDetails);
        }
        BuyerDetails buyerExisting = buyerDetailsRepository.findByBuyerName(gstRequest.getBuyerName());
        System.out.println("buyerExisting "+buyerExisting);
        if (sellerExisting == null) {
            BuyerDetails saveBuyerDetails = new BuyerDetails();
            saveBuyerDetails.setBuyerName(gstRequest.getBuyerName());
            saveBuyerDetails.setBuyerAddress(gstRequest.getBuyerAddress());
            saveBuyerDetails.setBuyerPhone(gstRequest.getBuyerPhone());
            saveBuyerDetails.setBuyerStateCode(gstRequest.getBuyerStateCode());
            saveBuyerDetails.setBuyerGstin(gstRequest.getBuyerGstin());
            saveBuyerDetails.setBuyerPan(gstRequest.getBuyerPan());
            saveBuyerDetails.setBuyerDlNo1(gstRequest.getBuyerDlNo1());
            saveBuyerDetails.setBuyerDlNo2(gstRequest.getBuyerDlNo2());
            buyerDetailsRepository.save(saveBuyerDetails);
        }
        return invoiceRepository.save(gstRequest);
    }


    @Override
    public boolean deleteInvoice(Long id) {
        Optional<GstInvoice> optionalInvoice = invoiceRepository.findById(id);

        if (optionalInvoice.isPresent()) {
            invoiceRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}