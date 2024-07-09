package com.kth.project_dollarstore.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kth.project_dollarstore.dto.ReceiptMetaDataDto;
import com.kth.project_dollarstore.model.ReceiptMetaData;
import com.kth.project_dollarstore.repository.CustomerRepository;
import com.kth.project_dollarstore.repository.ReceiptRepository;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public ReceiptMetaData saveReceipt(MultipartFile file, String butik, String datum, String tid, String kvittonummer, Float totalPrice, Integer customerId) throws IOException {
        ReceiptMetaData receipt = new ReceiptMetaData();
        receipt.setButik(butik);
        receipt.setDatum(datum);
        receipt.setTid(tid);
        receipt.setKvittonummer(kvittonummer);
        receipt.setTotalPrice(totalPrice);
        receipt.setReceiptImage(file.getBytes());
        receipt.setCustomerId(customerId);
        return receiptRepository.save(receipt);
    }

    public List<ReceiptMetaDataDto> getReceiptsByCustomerId(Integer customerId) {
        return receiptRepository.findReceiptDtosByCustomerId(customerId);
    }

    public ReceiptMetaData getReceiptById(Long id) {
        return receiptRepository.findById(id).orElse(null);
    }  

    public ResponseEntity<Void> deleteReceipt(Integer customerId, Long receiptId) {
        Optional<ReceiptMetaData> receipt = receiptRepository.findById(receiptId);
        if (receipt.isPresent() && receipt.get().getCustomerId().equals(customerId)) {
            receiptRepository.deleteById(receiptId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
