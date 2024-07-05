package com.kth.project_dollarstore.controller;

import java.io.IOException;
import java.util.List;
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

    @PostMapping
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
    public List<ReceiptMetaData> getCustomerReceipts(@PathVariable("customerId") Integer customerId) {
        List<ReceiptMetaData> receipts = receiptService.getReceiptsByCustomerId(customerId);
        for (ReceiptMetaData receipt : receipts) {
            receipt.setReceiptImageUrl("/api/v1/customers/" + customerId + "/receipts/image/" + receipt.getId());
        }
        return receipts;
    }

    @GetMapping("/{customerId}/receipts/image/{id}")
    public ResponseEntity<byte[]> getReceiptImage(@PathVariable("customerId") Integer customerId, @PathVariable Long id) {
        ReceiptMetaData receipt = receiptService.getReceiptById(id);

        if (receipt != null && receipt.getCustomer().getId().equals(customerId)) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentLength(receipt.getReceiptImage().length);
            return new ResponseEntity<>(receipt.getReceiptImage(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
