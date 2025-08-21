package com.medbillpro.serviceImpl;

import com.medbillpro.entity.Admin;
import com.medbillpro.repository.AdminRepository;
import com.medbillpro.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin saveAdmin(Admin admin) {
        // Check for duplicate email
        if (adminRepository.existsByEmail(admin.getEmail())) {
            throw new RuntimeException("Admin with email " + admin.getEmail() + " already exists");
        }
        return adminRepository.save(admin);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
}
