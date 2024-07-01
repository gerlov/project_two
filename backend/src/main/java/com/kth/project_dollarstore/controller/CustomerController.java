package com.kth.project_dollarstore.controller;


import org.springframework.web.bind.annotation.*;
import com.kth.project_dollarstore.model.Customer;
import com.kth.project_dollarstore.service.CustomerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("/{customerId}")
    public Optional getCustomerById(@PathVariable("customerId") Integer id){
        return customerService.getCustomerById(id);
    }

    // @GetMapping
    // public Optional getCustomerByEmail(@RequestBody String email){
    //     return customerService.getCustomerByEmail(email);
    // }

    @DeleteMapping("/{customerId}")
    public void deleteCustomerById(@PathVariable("customerId") Integer id){
        customerService.deleteCustomerById(id);
    }

    @PutMapping("/{customerId}")
    public Optional updateCustomerById(@PathVariable("customerId") Integer id, @RequestBody Customer customer){
        return customerService.updateCustomerById(id, customer);
    }
}