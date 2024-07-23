package com.kth.project_dollarstore.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kth.project_dollarstore.dto.ReceiptMetaDataDto;
import com.kth.project_dollarstore.model.ReceiptMetaData;
import com.kth.project_dollarstore.repository.ReceiptRepository;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    public ReceiptMetaData saveReceipt(MultipartFile file, String butik, Date datum, String tid, String kvittonummer, Float totalPrice, Integer customerId) throws IOException {
        ReceiptMetaData receipt = new ReceiptMetaData();
        receipt.setButik(butik);
        receipt.setDatum(datum);
        receipt.setTid(tid);
        receipt.setKvittonummer(kvittonummer);
        receipt.setTotalPrice(totalPrice);
        receipt.setReceiptImage(file.getBytes());
        receipt.setCustomerId(customerId);
        receipt.setNotes("");
        return receiptRepository.save(receipt);
    }

    public List<ReceiptMetaDataDto> getReceiptsByCustomerId(Integer customerId) {
        return receiptRepository.findReceiptDtosByCustomerId(customerId);
    }

    public ReceiptMetaData getReceiptById(Long id) {
        return receiptRepository.findById(id).orElse(null);
    }  

    public Optional<ReceiptMetaData> updateReceiptById(Long receiptId, ReceiptMetaData receipt) {
        Optional<ReceiptMetaData> updatingReceipt = receiptRepository.findById(receiptId);
        if (updatingReceipt.isPresent()) {
            ReceiptMetaData n_rct = updatingReceipt.get();
            if (receipt.getButik() != null) {
                n_rct.setButik(receipt.getButik());
            }
            if (receipt.getDatum() != null) {
                n_rct.setDatum(receipt.getDatum());
            }
            if (receipt.getTid() != null) {
                n_rct.setTid(receipt.getTid());
            }
            if (receipt.getKvittonummer() != null) {
                n_rct.setKvittonummer(receipt.getKvittonummer());
            }
            if (receipt.getTotalPrice() != null) {
                n_rct.setTotalPrice(receipt.getTotalPrice());
            }
            if (receipt.getNotes() != null) {
                n_rct.setNotes(receipt.getNotes());
            }
            receiptRepository.save(n_rct);
        }
        return updatingReceipt;
    }

    public ResponseEntity<Void> deleteReceipt(Integer customerId, Long receiptId) {
        Optional<ReceiptMetaData> receipt = receiptRepository.findById(receiptId);
        if (receipt.isPresent() && receipt.get().getCustomerId().equals(customerId)) {
            receiptRepository.deleteById(receiptId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public Optional<ReceiptMetaData> getReceiptForCustomer(Integer customerId, Long receiptId) {
        Optional<ReceiptMetaData> receipt = receiptRepository.findById(receiptId);
        if (receipt.isPresent() && receipt.get().getCustomerId().equals(customerId)) {
            return receipt;
        }
        return Optional.empty();
    }
    

}
