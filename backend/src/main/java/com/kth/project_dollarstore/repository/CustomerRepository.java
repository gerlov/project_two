package com.kth.project_dollarstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kth.project_dollarstore.model.Customer;

public interface CustomerRepository
            extends JpaRepository<Customer,Integer> {
                Optional<Customer> findByEmail(String email);   

}
