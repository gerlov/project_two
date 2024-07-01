package com.kth.project_dollarstore.controller;

import com.kth.project_dollarstore.model.ReceiptMetaData;
import com.kth.project_dollarstore.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/receipts")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @PostMapping
    public ReceiptMetaData saveReceipt(@RequestBody ReceiptMetaData receiptMetaData) {
        return receiptService.saveReceipt(receiptMetaData);
    }
}
