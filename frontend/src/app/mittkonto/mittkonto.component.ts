import { Component, OnInit } from '@angular/core';
import { CustomerService, Customer } from '../customer.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router'; 

@Component({
  selector: 'app-mittkonto',
  templateUrl: './mittkonto.component.html',
  styleUrls: ['./mittkonto.component.css']
})
export class MittkontoComponent implements OnInit {
  customer: Customer | null = null;
  isEditing: boolean = false;

  constructor(
    private customerService: CustomerService,
    private http: HttpClient,
    private router: Router 
  ) { }

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

  // bara en ide, vi kanske vill spara anledningar via checkbox och lagra i vår databas för att ge dollarstore varför kunder avslutar kontot.
  // Skapar en fake för nu vi kan ta bort senare
  deleteAccount(): void {
    if (confirm('This action is permanent. Are you sure you want to delete your account?')) {
      const customerId = localStorage.getItem('customerId');
      if (customerId) {
        this.http.delete(`http://localhost:8080/api/v1/customers/${customerId}`).subscribe(() => {
          console.log('Account deleted successfully');
          localStorage.removeItem('customerId');
          this.askForDeleteReason();
        }, error => {
          console.error('Error deleting account:', error);
        });
      }
    }
  }
  
  askForDeleteReason(): void {
    const reasons = [
      'Did not find products I wanted',
      'Poor customer service experience',
      'Found better prices elsewhere',
      'Other (please specify)'
    ];
  
    const reason = prompt(
      'Why did you want to delete your account?\n\n' +
      '1. ' + reasons[0] + '\n' +
      '2. ' + reasons[1] + '\n' +
      '3. ' + reasons[2] + '\n' +
      '4. ' + reasons[3] + '\n\n' +
      'Please enter to mke us improve:'
    );
    this.router.navigate(['/loginchoose']);
  }
}
