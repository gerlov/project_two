package com.kth.project_dollarstore.service;

import com.kth.project_dollarstore.model.ReceiptMetaData;
import com.kth.project_dollarstore.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    public ReceiptMetaData saveReceipt(MultipartFile file, String butik, String datum, String tid, String kvittonummer, Float totalPrice) throws IOException {
        ReceiptMetaData receipt = new ReceiptMetaData();
        receipt.setButik(butik);
        receipt.setDatum(datum);
        receipt.setTid(tid);
        receipt.setKvittonummer(kvittonummer);
        receipt.setTotalPrice(totalPrice);
        receipt.setReceiptImage(file.getBytes());
        return receiptRepository.save(receipt);
    }

    public ReceiptMetaData getReceiptById(Long id) {
        return receiptRepository.findById(id).orElse(null);
    }
}
