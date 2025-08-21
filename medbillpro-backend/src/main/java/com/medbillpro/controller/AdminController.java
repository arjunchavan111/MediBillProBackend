package com.medbillpro.controller;

import com.medbillpro.apiResponse.ApiResponse;
import com.medbillpro.constants.URLMapping;
import com.medbillpro.dto.AdminRequest;
import com.medbillpro.dto.LoginRequest;
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
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {

        ApiResponse apiResponse =new ApiResponse<>();
        if ("admin".equals(loginRequest.getUsername()) && "admin".equals(loginRequest.getPassword())) {
            apiResponse.setMessage("success");
            apiResponse.setSuccess(true);
            apiResponse.setData("Welcome, Admin!");
            apiResponse.setStatus(1);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
    }

    @PostMapping(URLMapping.ADMIN_DETAILS)
    public ResponseEntity adminDetails(@RequestBody AdminRequest adminRequest) {

        ApiResponse apiResponse =new ApiResponse<>();
            apiResponse.setMessage("success");
            apiResponse.setSuccess(true);
            apiResponse.setData("Welcome, Admin!");
            apiResponse.setStatus(1);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping(URLMapping.GET_ADMIN_DETAILS)
    public ResponseEntity<ApiResponse>  getAmindetails() {
        List<Admin> admins = adminService.getAllAdmins();
        ApiResponse apiResponse =new ApiResponse<>();
        apiResponse.setMessage("success");
        apiResponse.setSuccess(true);
        apiResponse.setData(admins);
        apiResponse.setStatus(1);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
