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
    const customerId = 1; // Change this to the ID you want to fetch
    this.customerService.getCustomerById(customerId).subscribe((data: Customer) => {
      this.customer = data;
    });
  }

  onEditButtonClick(customer: Customer): void {
    // Implement edit functionality if needed
    console.log('Edit button clicked for customer:', customer);
  }
}
