import { Component, OnInit } from '@angular/core';
import { CustomerService, Customer } from '../customer.service';

@Component({
  selector: 'app-mittkonto',
  templateUrl: './mittkonto.component.html',
  styleUrls: ['./mittkonto.component.css']
})
export class MittkontoComponent implements OnInit {
  customer: Customer | null = null;

  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {
    const customerId = 1; // Byt beroend på kund
    this.customerService.getCustomerById(customerId).subscribe((data: Customer) => {
      this.customer = data;
      this.customer.password = "*".repeat(8);
      this.customer.phonecode = "+46";
    });
  }

  onEditButtonClick(customer: Customer): void {
    console.log('Edit button clicked for customer:', customer);
  }
}
