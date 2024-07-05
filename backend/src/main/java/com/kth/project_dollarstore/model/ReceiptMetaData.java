package com.kth.project_dollarstore.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Transient;

@Entity
public class ReceiptMetaData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receipt_id_sequence")
    @SequenceGenerator(name = "receipt_id_sequence", sequenceName = "receipt_id_sequence", allocationSize = 1)
    private Long id;
    private String butik;
    private String datum;
    private String tid;
    private String kvittonummer;
    private Float totalPrice;

    @Lob
    @Column(name = "receipt_image")
    @JsonIgnore
    private byte[] receiptImage;

    private Integer customerId;

    @Transient
    private String receiptImageUrl;

    public ReceiptMetaData() {
    }

    public ReceiptMetaData(Long id, String butik, String datum, String tid, String kvittonummer, Float totalPrice, byte[] receiptImage, Integer customerId) {
        this.id = id;
        this.butik = butik;
        this.datum = datum;
        this.tid = tid;
        this.kvittonummer = kvittonummer;
        this.totalPrice = totalPrice;
        this.receiptImage = receiptImage;
        this.customerId = customerId;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getButik() {
        return butik;
    }

    public void setButik(String butik) {
        this.butik = butik;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getKvittonummer() {
        return kvittonummer;
    }

    public void setKvittonummer(String kvittonummer) {
        this.kvittonummer = kvittonummer;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public byte[] getReceiptImage() {
        return receiptImage;
    }

    public void setReceiptImage(byte[] receiptImage) {
        this.receiptImage = receiptImage;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getReceiptImageUrl() {
        return "/api/v1/customers/" + this.customerId + "/receipts/image/" + this.id;
    }

    @Override
    public String toString() {
        return "ReceiptMetaData [id=" + id + ", butik=" + butik + ", datum=" + datum + ", tid=" + tid + ", kvittonummer=" + kvittonummer + ", totalPrice=" + totalPrice + "]";
    }

    public void setReceiptImageUrl(String receiptImageUrl) {
        this.receiptImageUrl = receiptImageUrl;
    }
}
