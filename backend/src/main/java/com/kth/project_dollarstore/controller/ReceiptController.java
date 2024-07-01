package com.kth.project_dollarstore.controller;

import com.kth.project_dollarstore.model.ReceiptMetaData;
import com.kth.project_dollarstore.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadReceipt(
        @RequestParam("file") MultipartFile file,
        @RequestParam("butik") String butik,
        @RequestParam("datum") String datum,
        @RequestParam("tid") String tid,
        @RequestParam("kvittonummer") String kvittonummer,
        @RequestParam("totalPrice") Float totalPrice
    ) throws IOException {
        ReceiptMetaData receipt = receiptService.saveReceipt(file, butik, datum, tid, kvittonummer, totalPrice);
        Map<String, Object> response = new HashMap<>();
        response.put("id", receipt.getId());
        response.put("butik", receipt.getButik());
        response.put("datum", receipt.getDatum());
        response.put("tid", receipt.getTid());
        response.put("kvittonummer", receipt.getKvittonummer());
        response.put("totalPrice", receipt.getTotalPrice());
        response.put("receiptImageUrl", "/receipts/image/" + receipt.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

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
