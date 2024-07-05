package com.kth.project_dollarstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.kth.project_dollarstore.model.Customer;
import com.kth.project_dollarstore.repository.DatabaseController;

@Service
public class CustomerService {

    @Autowired
    private DatabaseController databaseController;

    public Customer addCustomer(Customer customer) {  
        encryptPassword(customer);  // Encrypt the password before saving
        return databaseController.save(customer);
    }

    private void encryptPassword(Customer customer) {
        String encryptedPassword = BCrypt.hashpw(customer.getPassword(), BCrypt.gensalt());
        customer.setPassword(encryptedPassword);
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
            n_cs.setAddress(customer.getAddress());
            n_cs.setAge(customer.getAge());
            n_cs.setDob(customer.getDob());
            n_cs.setEmail(customer.getEmail());
            n_cs.setName(customer.getName());
            n_cs.setPhoneNumber(customer.getPhoneNumber());
            n_cs.setPostalCode(customer.getPostalCode());
            databaseController.save(n_cs);

        }
        return updatingCustomer;
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return databaseController.findByEmail(email);
    }  

    public String login(String email, String password) {
        Optional<Customer> customerOptional = getCustomerByEmail(email);


        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            
            // dbug prints, temove 
            System.out.println("Customer details, retreived from the db: ");
            System.out.println(customer.toString());

            // moved paswd verification here to the service layer   
            // so Customer focuses on just holding customer data
            // and CutomerController focuses on making post/get requests and redirecting 
            if (BCrypt.checkpw(password, customer.getPassword()))  {   
                return "Login successful";
            } else {
                return "Invalid credentials";
            }
        }
        return "User not found";
    }
}







