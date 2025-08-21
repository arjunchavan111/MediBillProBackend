package com.medbillpro.controller;

import com.medbillpro.apiResponse.ApiResponse;
import com.medbillpro.constants.URLMapping;
import com.medbillpro.dto.PurchaseDTO;
import com.medbillpro.dto.PurchaseSummaryDTO;
import com.medbillpro.entity.Purchase;
import com.medbillpro.repository.PurchaseRepository;
import com.medbillpro.service.PurchaseService;
import com.medbillpro.serviceImpl.PurchaseDtoSeviceImpl;
import com.medbillpro.serviceImpl.PurchaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(URLMapping.PURCHASE_API_BASE)
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    PurchaseDtoSeviceImpl purchaseDtoSeviceImpl;
    @Autowired
    PurchaseServiceImpl purchaseServiceImpl;

    private static final String ACCESS_TOKEN = "YOUR_WHATSAPP_CLOUD_API_ACCESS_TOKEN";
    private static final String PHONE_NUMBER_ID = "7875689635";

    private static final String MEDIA_UPLOAD_URL = "https://graph.facebook.com/v17.0/{7756970203}/media";
    private static final String SEND_MESSAGE_URL = "https://graph.facebook.com/v17.0/{7756970203}/messages";


    @GetMapping(URLMapping.GET_PURCHASE_DETAILS)
    public ResponseEntity<ApiResponse> getAllPurchases() {
        ApiResponse apiResponse = new ApiResponse<>();
        List<PurchaseDTO> purchases = purchaseServiceImpl.getAllPurchasesNested();


        apiResponse.setMessage("Success");
        apiResponse.setSuccess(true);
        apiResponse.setData(purchases);
        apiResponse.setStatus(1);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }



    @PostMapping(URLMapping.ADD_PURCHASE_DETAILS)
    public ResponseEntity<ApiResponse> savePurchase(@RequestBody Purchase purchase) {
        try {
            Purchase savedPurchase = purchaseService.savePurchase(purchase);

            ApiResponse apiResponse = new ApiResponse<>();
            if (savedPurchase.getPurchaseId() == null) {
                apiResponse.setMessage("Purchase not saved");
                apiResponse.setSuccess(false);
                apiResponse.setStatus(0);
                return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
            } else {
                //PurchaseDTO dto = purchaseDtoSeviceImpl.convertToDTO(savedPurchase);
                apiResponse.setMessage("Order Success");
                apiResponse.setSuccess(true);
                apiResponse.setStatus(1);
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            }
        }catch (Exception e) {
            ApiResponse apiResponse = new ApiResponse<>();
            apiResponse.setMessage("Error: " + e.getMessage());
            apiResponse.setSuccess(false);
            apiResponse.setStatus(0);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(URLMapping.DELETE_PURCHASE_DETAILS)
    public ResponseEntity<ApiResponse> deletePurchases(@RequestBody List<Long> ids) {
        ApiResponse apiResponse = new ApiResponse();

        if (ids == null || ids.isEmpty()) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("Please Select The Row ");
            apiResponse.setStatus(1);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        purchaseService.deletePurchasesByIds(ids);
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Deleted purchases with IDs: " + ids);
        apiResponse.setStatus(1);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @GetMapping(URLMapping.GET_ADMIN_DETAILS)
    public ResponseEntity<ApiResponse>  getAmindetails() {
        //List<Admin> admins = adminService.getAllAdmins();
        ApiResponse apiResponse =new ApiResponse<>();
        apiResponse.setMessage("success");
        apiResponse.setSuccess(true);
        apiResponse.setData("Sample Admin Data"); // Replace with actual admin data retrieval
        apiResponse.setStatus(1);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/sendInvoice")
    public String sendLocalInvoice() {
        RestTemplate restTemplate = new RestTemplate();

        // Step 1: Upload local PDF
        File pdfFile = new File("C:\\Users\\dell\\OneDrive\\Desktop\\DATA\\invoice.pdf");
        FileSystemResource resource = new FileSystemResource(pdfFile);

        HttpHeaders uploadHeaders = new HttpHeaders();
        uploadHeaders.setBearerAuth(ACCESS_TOKEN);
        uploadHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> uploadBody = new LinkedMultiValueMap<>();
        uploadBody.add("file", resource);
        uploadBody.add("type", "document");
        uploadBody.add("messaging_product", "whatsapp");

        HttpEntity<MultiValueMap<String, Object>> uploadRequest = new HttpEntity<>(uploadBody, uploadHeaders);
        String uploadUrl = MEDIA_UPLOAD_URL.replace("{phone_number_id}", PHONE_NUMBER_ID);

        ResponseEntity<String> uploadResponse = restTemplate.postForEntity(uploadUrl, uploadRequest, String.class);
        System.out.println("Upload Response: " + uploadResponse.getBody());

        // ✅ Extract media_id from JSON
        String mediaId = extractMediaId(uploadResponse.getBody());

        // Step 2: Send message using media_id
        HttpHeaders msgHeaders = new HttpHeaders();
        msgHeaders.setBearerAuth(ACCESS_TOKEN);
        msgHeaders.setContentType(MediaType.APPLICATION_JSON);

        String messageJson = """
        {
            "messaging_product": "whatsapp",
            "to": "917875689635",
            "type": "document",
            "document": {
                "id": "%s",
                "caption": "Here is your invoice",
                "filename": "invoice.pdf"
            }
        }
        """.formatted(mediaId);

        HttpEntity<String> msgRequest = new HttpEntity<>(messageJson, msgHeaders);
        String sendUrl = SEND_MESSAGE_URL.replace("{phone_number_id}", PHONE_NUMBER_ID);

        ResponseEntity<String> msgResponse = restTemplate.postForEntity(sendUrl, msgRequest, String.class);
        return msgResponse.getBody();
    }

    private String extractMediaId(String json) {
        // ✅ In production, parse with Jackson or Gson
        int start = json.indexOf("id\":\"") + 5;
        int end = json.indexOf("\"", start);
        return json.substring(start, end);
    }
}