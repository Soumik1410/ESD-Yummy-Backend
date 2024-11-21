package com.prashantjain.yummyrest.repo;

import com.prashantjain.yummyrest.entity.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM customer WHERE email = ?1", nativeQuery = true)
    Customer findByEmail(String email);

}