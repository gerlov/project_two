package com.kth.project_dollarstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.io.Serializable;

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

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;

    @Transient
    private String receiptImageUrl;

    public ReceiptMetaData() {
    }

    public ReceiptMetaData(Long id, String butik, String datum, String tid, String kvittonummer, Float totalPrice, byte[] receiptImage, Customer customer) {
        this.id = id;
        this.butik = butik;
        this.datum = datum;
        this.tid = tid;
        this.kvittonummer = kvittonummer;
        this.totalPrice = totalPrice;
        this.receiptImage = receiptImage;
        this.customer = customer;
    }

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    

    public String getReceiptImageUrl() {
        return "/api/v1/customers/" + this.customer.getId() + "/receipts/image/" + this.id;
    }

    @Override
    public String toString() {
        return "ReceiptMetaData [id=" + id + ", butik=" + butik + ", datum=" + datum + ", tid=" + tid + ", kvittonummer=" + kvittonummer + ", totalPrice=" + totalPrice + "]";
    }

    public void setReceiptImageUrl(String receiptImageUrl) {
        this.receiptImageUrl = receiptImageUrl;
    }
}
