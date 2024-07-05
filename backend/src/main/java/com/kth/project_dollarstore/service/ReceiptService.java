package com.kth.project_dollarstore.service;

import com.kth.project_dollarstore.model.Customer;
import com.kth.project_dollarstore.model.ReceiptMetaData;
import com.kth.project_dollarstore.repository.CustomerRepository;
import com.kth.project_dollarstore.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public ReceiptMetaData saveReceipt(MultipartFile file, String butik, String datum, String tid, String kvittonummer, Float totalPrice, Integer customerId) throws IOException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        ReceiptMetaData receipt = new ReceiptMetaData();
        receipt.setButik(butik);
        receipt.setDatum(datum);
        receipt.setTid(tid);
        receipt.setKvittonummer(kvittonummer);
        receipt.setTotalPrice(totalPrice);
        receipt.setReceiptImage(file.getBytes());
        receipt.setCustomer(customer);
        return receiptRepository.save(receipt);
    }

    public List<ReceiptMetaData> getReceiptsByCustomerId(Integer customerId) {
        return receiptRepository.findByCustomerId(customerId);
    }

    public ReceiptMetaData getReceiptById(Long id) {
        return receiptRepository.findById(id).orElse(null);
    }
}
