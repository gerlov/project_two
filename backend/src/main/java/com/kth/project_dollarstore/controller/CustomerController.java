package com.kth.project_dollarstore.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kth.project_dollarstore.dto.ReceiptMetaDataDto;
import com.kth.project_dollarstore.model.Customer;
import com.kth.project_dollarstore.model.ReceiptMetaData;
import com.kth.project_dollarstore.service.CustomerService;
import com.kth.project_dollarstore.service.ReceiptService;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final ReceiptService receiptService;

    public CustomerController(CustomerService customerService, ReceiptService receiptService) {
        this.customerService = customerService;
        this.receiptService = receiptService;
    }

    @PostMapping("/register")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("/{customerId}")
    public Optional getCustomerById(@PathVariable("customerId") Integer id) {
        return customerService.getCustomerById(id);
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomerById(@PathVariable("customerId") Integer id) {
        customerService.deleteCustomerById(id);
    }

    @SuppressWarnings("rawtypes")
    @PutMapping("/{customerId}")
    public Optional updateCustomerById(@PathVariable("customerId") Integer id, @RequestBody Customer customer) {
        return customerService.updateCustomerById(id, customer);
    }

    @PostMapping("/login")
    public String login(@RequestBody Customer customerDetails) {
        return customerService.login(customerDetails.getEmail(), customerDetails.getPassword());
    }  

    @PostMapping("/{customerId}/upload")
    public ReceiptMetaData uploadReceipt(
        @PathVariable("customerId") Integer customerId,
        @RequestParam("file") MultipartFile file,
        @RequestParam("butik") String butik,
        @RequestParam("datum") String datum,
        @RequestParam("tid") String tid,
        @RequestParam("kvittonummer") String kvittonummer,
        @RequestParam("total") Float totalPrice
    ) throws IOException {
        return receiptService.saveReceipt(file, butik, datum, tid, kvittonummer, totalPrice, customerId);
    }

    @GetMapping("/{customerId}/receipts")
    public List<ReceiptMetaDataDto> getCustomerReceipts(@PathVariable("customerId") Integer customerId) {
        return receiptService.getReceiptsByCustomerId(customerId);
    }

    @PutMapping("/{customerId}/receipts/{receiptId}/edit")
    public ResponseEntity<ReceiptMetaData> updateReceipt(
            @PathVariable("customerId") Integer customerId,
            @PathVariable("receiptId") Long receiptId,
            @RequestBody ReceiptMetaData receipt) {
        Optional<ReceiptMetaData> updatedReceipt = receiptService.updateReceiptById(receiptId, receipt);
        if (updatedReceipt.isPresent()) {
            return ResponseEntity.ok(updatedReceipt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{customerId}/receipts/image/{id}")
    public ResponseEntity<byte[]> getReceiptImage(@PathVariable("customerId") Integer customerId, @PathVariable Long id) {
        ReceiptMetaData receipt = receiptService.getReceiptById(id);

        if (receipt != null && receipt.getCustomerId().equals(customerId)) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentLength(receipt.getReceiptImage().length);
            return new ResponseEntity<>(receipt.getReceiptImage(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{customerId}/receipts/{receiptId}")
    public ResponseEntity<Void> deleteReceipt(@PathVariable("customerId") Integer customerId, @PathVariable("receiptId") Long receiptId) {
        return receiptService.deleteReceipt(customerId, receiptId);
    }

    @GetMapping("/{customerId}/receipts/{receiptId}/download")
    public ResponseEntity<byte[]> downloadReceipt(@PathVariable("customerId") Integer customerId, @PathVariable("receiptId") Long receiptId) {
        Optional<ReceiptMetaData> receipt = receiptService.getReceiptForCustomer(customerId, receiptId);

        if (receipt.isPresent()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "receipt_" + receiptId + ".jpg");
            headers.setContentLength(receipt.get().getReceiptImage().length);
            return new ResponseEntity<>(receipt.get().getReceiptImage(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        customerService.sendPasswordResetEmail(email);
        return ResponseEntity.ok("Password reset email sent.");
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> savePassword(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String newPassword = request.get("password");
    
        try {
            Optional<Customer> customerOptional = customerService.findCustomerByResetToken(token);
            if (customerOptional.isPresent()) {
                Customer customer = customerOptional.get();
                customerService.updatePassword(customer, newPassword);
                return ResponseEntity.ok("Psssword changed.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token invalid.");
            }
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error upon password chenge.");
        }
    }
    

}


