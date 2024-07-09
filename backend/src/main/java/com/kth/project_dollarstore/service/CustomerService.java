package com.kth.project_dollarstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.kth.project_dollarstore.model.Customer;
import com.kth.project_dollarstore.repository.CustomerRepository;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // public Customer addCustomer(Customer customer) {
    //     return customerRepository.save(customer);

    public Customer addCustomer(Customer customer) {  
        encryptPassword(customer);  // Encrypt the password before saving
        return customerRepository.save(customer);

    }

    private void encryptPassword(Customer customer) {
        String encryptedPassword = BCrypt.hashpw(customer.getPassword(), BCrypt.gensalt());
        customer.setPassword(encryptedPassword);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Integer id) {
        return customerRepository.findById(id);
    }

    public void deleteCustomerById(Integer id) {
        customerRepository.deleteById(id);
    }

    public Optional<Customer> updateCustomerById(Integer id, Customer customer) {
        Optional<Customer> updatingCustomer = customerRepository.findById(id);
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
            if(!(customer.getPassword() == null)){
                n_cs.setPassword(customer.getPassword());
                encryptPassword(n_cs);
            }
            customerRepository.save(n_cs);

        }
        return updatingCustomer;
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }  

    public String login(String email, String password) {
        Optional<Customer> customerOptional = getCustomerByEmail(email);


        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
        
            if (BCrypt.checkpw(password, customer.getPassword()))  {   
                return String.valueOf(customer.getId());
            } else {
                return "Invalid credentials";
            }
        }
        return "User not found";
    }
}







