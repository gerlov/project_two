package com.kth.project_dollarstore.controller;

import com.kth.project_dollarstore.model.ReceiptMetaData;
import com.kth.project_dollarstore.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/receipts")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getReceiptImage(@PathVariable Long id) {
        ReceiptMetaData receipt = receiptService.getReceiptById(id);

        if (receipt != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentLength(receipt.getReceiptImage().length);
            return new ResponseEntity<>(receipt.getReceiptImage(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
