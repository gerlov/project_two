import { Component, OnInit } from '@angular/core';
import { CustomerService, Customer } from '../customer.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-mittkonto',
  templateUrl: './mittkonto.component.html',
  styleUrls: ['./mittkonto.component.css']
})
export class MittkontoComponent implements OnInit {
  customer: Customer | null = null;
  isEditing: boolean = false;

  constructor(private customerService: CustomerService, private http: HttpClient) { }

  ngOnInit(): void {
    const customerId = localStorage.getItem('customerId');
    if (customerId !== null) {
      this.customerService.getCustomerById(parseInt(customerId, 10)).subscribe((data: Customer) => {
        this.customer = data;
        this.customer.password = "*".repeat(8);
        this.customer.phonecode = "+46";
      });
    } else {
      console.error("No customer id found");
    }
  }

  toggleEdit(): void {
    if (this.isEditing && this.customer) {
      const customerId = localStorage.getItem('customerId');
      this.http.put('http://localhost:8080/api/v1/customers/' + customerId, this.customer).subscribe(response => {
        console.log('Customer updated:', response);
        this.isEditing = false;
      }, error => {
        console.error('Error:', error);
      });
    } else {
      this.isEditing = true;
    }
  }

  deleteAccount(): void {
    if (confirm('This is permanent, are yo sure you want to continue?')) { // maybe we can give users some felts to choose from why they chose to delete and store in a table to see why users left
      console.log('Account deleted');
      // TODO: Implement fetch and apply backend logic here

      // TODO should go back to login or register thingy
    } else {
      console.log('Delete cancelled');
    }
  }
}
