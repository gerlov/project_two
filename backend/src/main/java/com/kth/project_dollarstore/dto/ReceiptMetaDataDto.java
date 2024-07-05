package com.kth.project_dollarstore.dto;

public class ReceiptMetaDataDto {
    private Long id;
    private String butik;
    private String datum;
    private String tid;
    private String kvittonummer;
    private Float totalPrice;
    private String receiptImageUrl;

    public ReceiptMetaDataDto(Long id, String butik, String datum, String tid, String kvittonummer, Float totalPrice, String receiptImageUrl) {
        this.id = id;
        this.butik = butik;
        this.datum = datum;
        this.tid = tid;
        this.kvittonummer = kvittonummer;
        this.totalPrice = totalPrice;
        this.receiptImageUrl = receiptImageUrl;
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

    public String getReceiptImageUrl() {
        return receiptImageUrl;
    }

    public void setReceiptImageUrl(String receiptImageUrl) {
        this.receiptImageUrl = receiptImageUrl;
    }
}
