package com.prashantjain.yummyrest.repo;

import com.prashantjain.yummyrest.entity.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
