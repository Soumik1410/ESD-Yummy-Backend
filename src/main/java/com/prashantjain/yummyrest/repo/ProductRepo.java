package com.prashantjain.yummyrest.repo;

import com.prashantjain.yummyrest.entity.Customer;
import com.prashantjain.yummyrest.entity.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM product where price >= 15 and price <= 30 order by price desc limit 2", nativeQuery = true)
    List<Product> findTop2ProductsByPriceBetween15And30();

}
