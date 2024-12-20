package com.prashantjain.yummyrest.repo;

import com.prashantjain.yummyrest.entity.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM customer WHERE email = ?1", nativeQuery = true)
    Customer findByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE customer SET access_token =?2 WHERE email = ?1", nativeQuery = true)
    void insertAccessToken(String email, String token);

    @Modifying
    @Transactional
    @Query(value = "UPDATE customer SET first_name =?2, last_name=?3, address=?4, city=?5, pincode=?6 WHERE email = ?1", nativeQuery = true)
    void updateCustomer(String email, String first_name, String last_name, String address, String city, String pincode);
}
