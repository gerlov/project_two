package com.kth.project_dollarstore.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

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
    private byte[] receiptImage;

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReceiptItems> items;

    public ReceiptMetaData() {
    }

    public ReceiptMetaData(Long id, String butik, String datum, String tid, String kvittonummer, Float totalPrice, byte[] receiptImage) {
        this.id = id;
        this.butik = butik;
        this.datum = datum;
        this.tid = tid;
        this.kvittonummer = kvittonummer;
        this.totalPrice = totalPrice;
        this.receiptImage = receiptImage;
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

    public List<ReceiptItems> getItems() {
        return items;
    }

    public void setItems(List<ReceiptItems> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ReceiptMetaData [id=" + id + ", butik=" + butik + ", datum=" + datum + ", tid=" + tid + ", kvittonummer=" + kvittonummer + ", totalPrice=" + totalPrice + ", items=" + items + "]";
    }
}
