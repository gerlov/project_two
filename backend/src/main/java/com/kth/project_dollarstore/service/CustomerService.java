package com.kth.project_dollarstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kth.project_dollarstore.model.Customer;
import com.kth.project_dollarstore.repository.DatabaseController;

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
            if(!(customer.getAddress() == null)){
                n_cs.setAddress(customer.getAddress());
            }
            if(!(customer.getAge() == null)){
                n_cs.setAge(customer.getAge());
            }
            if(!(customer.getName() == null)){
                n_cs.setName(customer.getName());
            }
            if(!(customer.getDob() == null)){
                n_cs.setDob(customer.getDob());
            }
            if(!(customer.getEmail() == null)){
                n_cs.setEmail(customer.getEmail());
            }
            if(!(customer.getPhoneNumber() == null)){
                n_cs.setPhoneNumber(customer.getPhoneNumber());
            }
            if(!(customer.getPostalCode() == null)){
                n_cs.setPostalCode(customer.getPostalCode());
            }
            databaseController.save(n_cs);

        }
        return updatingCustomer;
    }



}
