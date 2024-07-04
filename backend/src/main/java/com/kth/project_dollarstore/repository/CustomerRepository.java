package com.kth.project_dollarstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kth.project_dollarstore.model.Customer;

public interface CustomerRepository
            extends JpaRepository<Customer,Integer> {

}
