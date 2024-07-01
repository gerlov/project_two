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
    private String nr;
    private Float totalPrice;

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReceiptItems> items;

    

    public ReceiptMetaData(Long id, String butik, String datum, String tid, String nr, Float totalPrice,
            List<ReceiptItems> items) {
        this.id = id;
        this.butik = butik;
        this.datum = datum;
        this.tid = tid;
        this.nr = nr;
        this.totalPrice = totalPrice;
        this.items = items;
    }

    public ReceiptMetaData() {
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

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<ReceiptItems> getItems() {
        return items;
    }

    public void setItems(List<ReceiptItems> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ReceiptMetaData [id=" + id + ", butik=" + butik + ", datum=" + datum + ", tid=" + tid + ", nr=" + nr
                + ", totalPrice=" + totalPrice + ", items=" + items + "]";
    }

    
}
