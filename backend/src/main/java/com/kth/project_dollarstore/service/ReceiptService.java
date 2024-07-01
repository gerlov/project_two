package com.kth.project_dollarstore.service;

import com.kth.project_dollarstore.model.ReceiptMetaData;
import com.kth.project_dollarstore.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    public ReceiptMetaData saveReceipt(ReceiptMetaData receiptMetaData) {
        return receiptRepository.save(receiptMetaData);
    }
}
