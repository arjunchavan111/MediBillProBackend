package com.medbillpro.repository;

import com.medbillpro.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    boolean existsByEmail(String email);
}
