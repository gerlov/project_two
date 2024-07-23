package com.kth.project_dollarstore.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.kth.project_dollarstore.email.EmailService;
import com.kth.project_dollarstore.model.Customer;
import com.kth.project_dollarstore.model.PasswordResetToken;
import com.kth.project_dollarstore.repository.CustomerRepository;
import com.kth.project_dollarstore.repository.PasswordResetTokenRepository;

import jakarta.transaction.Transactional;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;


    public Customer addCustomer(Customer customer) {  
        encryptPassword(customer);
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

        public void createPasswordResetTokenForCustomer(Customer customer, String token) {
        PasswordResetToken myToken = new PasswordResetToken();
        myToken.setToken(token);
        myToken.setCustomer(customer);
        myToken.setExpiryDate(new Date(System.currentTimeMillis() + 3600000)); // 1 hour expiry
        tokenRepository.save(myToken);
    }

    @Async //bcs background action
    public void sendPasswordResetEmail(String email) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            String token = UUID.randomUUID().toString();
            
            createPasswordResetTokenForCustomer(customer, token);
            
            try {
                emailService.sendPasswordResetEmail(email, token);
            } catch (Exception e) {
                throw new RuntimeException("Failed", e);
            }
        } else {
            throw new IllegalArgumentException("User" + email + " not found.");
        }
    }
    

    public Optional<Customer> findCustomerByResetToken(String token) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token);

        if (resetToken != null && resetToken.getExpiryDate().after(new Date())) {
            return Optional.of(resetToken.getCustomer());
        } else {
            return Optional.empty();
        }
    }
    
    @Async //bcs background action
    @Transactional
    public void updatePassword(Customer customer, String newPassword) {
        try {
            customer.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
            customerRepository.save(customer);
            tokenRepository.deleteByCustomer(customer);
        } catch (Exception e) {
            throw new RuntimeException("Error updating password", e);
        }
    }
    
}







