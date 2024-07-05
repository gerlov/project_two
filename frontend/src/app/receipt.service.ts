import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Receipt {
  id: number;
  butik: string;
  datum: string;
  tid: string;
  kvittonummer: string;
  total: number;
  receiptImageUrl: string;
}

@Injectable({
  providedIn: 'root'
})
export class ReceiptService {
  private apiUrl = 'http://localhost:8080/api/v1/customers';

  constructor(private http: HttpClient) { }

  getReceiptsByCustomerId(customerId: number): Observable<Receipt[]> {
    return this.http.get<Receipt[]>(`${this.apiUrl}/${customerId}/receipts`);
  }

  getReceiptImage(customerId: number, receiptId: number): Observable<Blob> {
    return this.http.get(`${this.apiUrl}/${customerId}/receipts/image/${receiptId}`, { responseType: 'blob' });
  }

  getReceiptById(customerId: number, receiptId: number): Observable<Receipt> {
    return this.http.get<Receipt>(`${this.apiUrl}/${customerId}/receipts/${receiptId}`);
  }
}
