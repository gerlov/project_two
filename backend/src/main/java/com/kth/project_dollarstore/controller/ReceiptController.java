package com.kth.project_dollarstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kth.project_dollarstore.model.ReceiptMetaData;
import com.kth.project_dollarstore.service.ReceiptService;

@RestController
@RequestMapping("api/v1/receipts")
@CrossOrigin(origins = "http://34.88.85.176:4200")
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
