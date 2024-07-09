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
  showConfirmDialog = false;
  currentReceipt?: Receipt;

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

  onRemoveButtonClick(receipt: Receipt): void {
    console.log('Remove button clicked for receipt ID:', receipt);
    this.showConfirmDialog = true;
    this.currentReceipt = receipt; 
  }

  onUploadButtonClick(): void {
    console.log('Upload button clicked');
    // NOT IMPLEMENTED YET 
  }  

  removeReceipt(receipt: Receipt): void {
    console.log('Confirmed removal of receipt:', receipt);
    this.receiptService.deleteReceipt(parseInt(localStorage.getItem('customerId')!, 10), receipt.id)
      .subscribe({
        next: () => {
          console.log('Receipt removed successfully');
          this.receipts = this.receipts.filter(r => r.id !== receipt.id);
          delete this.receiptImages[receipt.id];
          this.showConfirmDialog = false;
        },
        error: (error) => console.error('Error removing receipt:', error)
      });
  }
  
  cancelRemove(): void {
    console.log('Removal cancelled');
    this.showConfirmDialog = false;
  }
  
}

