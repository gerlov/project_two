package com.kth.project_dollarstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kth.project_dollarstore.Database.DatabaseController;
import com.kth.project_dollarstore.model.Customer;

@Service
public class CustomerService {

    @Autowired
    private DatabaseController databaseController;

    public Customer addCustomer(Customer customer) {
        return databaseController.save(customer);
    }

    public List<Customer> getCustomers() {
        return databaseController.findAll();
    }

    public Optional<Customer> getCustomerById(Integer id) {
        return databaseController.findById(id);
    }

    public void deleteCustomerById(Integer id) {
        databaseController.deleteById(id);
    }

    public Optional<Customer> updateCustomerById(Integer id, Customer customer) {
        Optional<Customer> updatingCustomer = databaseController.findById(id);
        if(updatingCustomer.isPresent()){
            Customer n_cs = updatingCustomer.get();
            n_cs.setAdress(customer.getAdress());
            n_cs.setAge(customer.getAge());
            n_cs.setDob(customer.getDob());
            n_cs.setEmail(customer.getEmail());
            n_cs.setName(customer.getName());
            n_cs.setPhone_number(customer.getPhone_number());
            n_cs.setPostal_code(customer.getPostal_code());
            databaseController.save(n_cs);

        }
        return updatingCustomer;
    }



}
