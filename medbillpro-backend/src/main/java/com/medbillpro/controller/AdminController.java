package com.medbillpro.controller;

import com.medbillpro.apiResponse.ApiResponse;
import com.medbillpro.constants.URLMapping;
import com.medbillpro.dto.AdminRequest;
import com.medbillpro.entity.Admin;
import com.medbillpro.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(URLMapping.ADMIN_API_BASE)
public class AdminController {

    @Autowired
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping(URLMapping.ADMIN_LOGIN)
    public ResponseEntity<ApiResponse> login(@RequestBody AdminRequest loginRequest) {
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            if ("admin".equals(loginRequest.getUsername()) && "admin".equals(loginRequest.getPassword())) {
                apiResponse.setStatus(1);
                apiResponse.setMessage("Login successful.");
                apiResponse.setData("Welcome, Admin!");
            } else {
                apiResponse.setStatus(0);
                apiResponse.setErrorMessage("Invalid credentials.");
            }
        } catch (Exception e) {
            apiResponse.setStatus(0);
            apiResponse.setErrorMessage("Exception occurred: " + e.getMessage());
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(URLMapping.ADMIN_DETAILS)
    public ResponseEntity<ApiResponse> adminDetails(@RequestBody AdminRequest adminRequest) {
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            // You can add logic to process adminRequest if needed
            apiResponse.setStatus(1);
            apiResponse.setMessage("Admin details processed successfully.");
            apiResponse.setData("Welcome, Admin!");
        } catch (Exception e) {
            apiResponse.setStatus(0);
            apiResponse.setErrorMessage("Exception occurred: " + e.getMessage());
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping(URLMapping.GET_ADMIN_DETAILS)
    public ResponseEntity<ApiResponse> getAdminDetails() {
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            List<Admin> admins = adminService.getAllAdmins();
            apiResponse.setStatus(1);
            apiResponse.setMessage("Admin details fetched successfully.");
            apiResponse.setData(admins);
        } catch (Exception e) {
            apiResponse.setStatus(0);
            apiResponse.setErrorMessage("Exception occurred: " + e.getMessage());
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}