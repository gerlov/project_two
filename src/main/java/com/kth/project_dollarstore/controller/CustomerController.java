package com.kth.project_dollarstore.controller;


import org.springframework.web.bind.annotation.*;
import com.kth.project_dollarstore.model.Customer;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;
    record newCustomerRequest(
        String name,
        String email,
        Integer age
    ){}



    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> getCusomters(){
        return customerRepository.findAll();
    }


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

    @PutMapping("{customerId}")
    public void updateCustomerById(@RequestBody newCustomerRequest request, @PathVariable("customerId") Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setAge(request.age);
            customer.setEmail(request.email);
            customer.setName(request.name);
            customerRepository.save(customer);
        } else {
            throw new RuntimeException("Customer not found");
        }
    }
}