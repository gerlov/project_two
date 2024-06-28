package com.kth.project_dollarstore.controller;


import org.springframework.web.bind.annotation.*;
import com.kth.project_dollarstore.model.Customer;
import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> getCusomters(){
        return customerRepository.findAll();
    }

    record newCustomerRequest(
        String name,
        String email,
        Integer age
    ){}

    @PostMapping
    public void addCustomer(@RequestBody newCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name);
        customer.setEmail(request.email);
        customer.setAge(request.age);
        customerRepository.save(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomerById(@PathVariable("customerId") Integer id) {
        customerRepository.deleteById(id);
    }
}