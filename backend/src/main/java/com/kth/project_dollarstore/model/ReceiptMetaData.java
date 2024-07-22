package com.kth.project_dollarstore.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReceiptMetaData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receipt_id_sequence")
    @SequenceGenerator(name = "receipt_id_sequence", sequenceName = "receipt_id_sequence", allocationSize = 1)
    private Long id;
    private String butik;
    private Date datum;
    private String tid;
    private String kvittonummer;
    private Float totalPrice;
    private Integer customerId;

    @Lob
    @Column(name = "receipt_image")
    @JsonIgnore
    private byte[] receiptImage;
    
    public String getReceiptImageUrl() {
        return "/api/v1/customers/" + this.customerId + "/receipts/image/" + this.id;
    }
    @Override
    public String toString() {
        return "ReceiptMetaData [id=" + id + ", butik=" + butik + ", datum=" + datum + ", tid=" + tid + ", kvittonummer=" + kvittonummer + ", totalPrice=" + totalPrice + "]";
    }
}
