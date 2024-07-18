package com.kth.project_dollarstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kth.project_dollarstore.model.PasswordResetToken;
import com.kth.project_dollarstore.model.Customer;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
    void deleteByCustomer(Customer customer);
}
