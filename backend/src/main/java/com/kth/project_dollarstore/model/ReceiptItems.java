package com.kth.project_dollarstore.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class ReceiptItems implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_sequence")
    @SequenceGenerator(name = "item_id_sequence", sequenceName = "item_id_sequence", allocationSize = 1)
    private Long id;
    private String name;
    private Float price;

    @ManyToOne
    @JoinColumn(name = "receipt_id")
    private ReceiptMetaData receipt;

    public ReceiptItems() {
    }

    public ReceiptItems(Long id, String name, Float price, ReceiptMetaData receipt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.receipt = receipt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public ReceiptMetaData getReceipt() {
        return receipt;
    }

    public void setReceipt(ReceiptMetaData receipt) {
        this.receipt = receipt;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\tPrice:" + price;
    }

    
}
