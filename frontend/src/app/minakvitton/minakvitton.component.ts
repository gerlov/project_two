import { Component, OnInit } from '@angular/core';
import { ReceiptService, Receipt } from '../receipt.service';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-minakvitton',
  templateUrl: './minakvitton.component.html',
  styleUrls: ['./minakvitton.component.css']
})
export class MinakvittonComponent implements OnInit {
  receipts: Receipt[] = [];
  receiptImages: { [key: number]: SafeUrl } = {};

  constructor(private receiptService: ReceiptService, private sanitizer: DomSanitizer) { }

  ngOnInit(): void {
    const customerId = localStorage.getItem('customerId');
    if(customerId !== null){
      this.loadReceipts(parseInt(customerId,10));
    } else {
      console.error("No customer id found");
    }
  }

  loadReceipts(customerId: number): void {
    this.receiptService.getReceiptsByCustomerId(customerId).subscribe((receipts: Receipt[]) => {
      this.receipts = receipts;
      receipts.forEach(receipt => {
        this.loadReceiptImage(customerId, receipt.id);
      });
    });
  }

  loadReceiptImage(customerId: number, receiptId: number): void {
    this.receiptService.getReceiptImage(customerId, receiptId).subscribe(imageBlob => {
      const url = URL.createObjectURL(imageBlob);
      this.receiptImages[receiptId] = this.sanitizer.bypassSecurityTrustUrl(url);
    });
  }

  onEditButtonClick(receipt : Receipt) {
    console.log('Edit button clicked for receipt:', receipt);
  }
}
