import { Component, OnInit } from '@angular/core';
import { CustomerService, Customer } from '../customer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-mittkonto',
  templateUrl: './mittkonto.component.html',
  styleUrls: ['./mittkonto.component.css']
})
export class MittkontoComponent implements OnInit {
  customer: Customer | null = null;

  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {
    const customerId = localStorage.getItem('customerId');
    if(customerId !== null){
      this.customerService.getCustomerById(parseInt(customerId, 10)).subscribe((data: Customer) => {
        this.customer = data;
        this.customer.password = "*".repeat(8);
        this.customer.phonecode = "+46";
      });
    } else {
      console.error("No customer id found");
    }
  }

  onEditButtonClick(customer: Customer): void {
    console.log('Edit button clicked for customer:', customer);
  }
}
