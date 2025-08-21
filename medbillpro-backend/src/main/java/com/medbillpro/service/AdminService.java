package com.medbillpro.service;

import com.medbillpro.entity.Admin;

import java.util.List;

public interface AdminService {
    Admin saveAdmin(Admin admin);
    List<Admin> getAllAdmins();
}
