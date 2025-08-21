package com.medbillpro.controller;

import com.medbillpro.apiResponse.ApiResponse;
import com.medbillpro.constants.URLMapping;
import com.medbillpro.entity.Admin;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.io.FileSystemResource;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/whatsapp")
public class WhatsAppController {

    private static final String ACCESS_TOKEN = "YOUR_WHATSAPP_CLOUD_API_ACCESS_TOKEN";
    private static final String PHONE_NUMBER_ID = "7875689635";

    private static final String MEDIA_UPLOAD_URL = "https://graph.facebook.com/v17.0/{7756970203}/media";
    private static final String SEND_MESSAGE_URL = "https://graph.facebook.com/v17.0/{7756970203}/messages";

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

    @PostMapping("/send-local-invoice")
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
