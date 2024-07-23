package com.kth.project_dollarstore.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptMetaDataDto {
    private Long id;
    private String butik;
    private Date datum;
    private String tid;
    private String kvittonummer;
    private Float totalPrice;
    private String notes;
    private String receiptImageUrl;    
}
