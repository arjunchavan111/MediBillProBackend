package com.medbillpro.repository;

import com.medbillpro.entity.Product;
import com.medbillpro.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
