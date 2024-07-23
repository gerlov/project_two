import { Component, OnInit } from '@angular/core';
import { CustomerService, Customer } from '../customer.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { StorageService } from '../storage.service';

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
    private router: Router,
    private storageService: StorageService
  ) { }

  ngOnInit(): void {
    const customerId = this.storageService.getItem('customerId');
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
      const customerId = this.storageService.getItem('customerId');
      if (customerId) {
        this.customerService.updateCustomer(parseInt(customerId, 10), this.customer).subscribe(response => {
          console.log('Customer updated:', response);
          this.isEditing = false;
        }, error => {
          console.error('Error:', error);
        });
      }
    } else {
      this.isEditing = true;
    }
  }

  deleteAccount(): void {
    if (confirm('This action is permanent. Are you sure you want to delete your account?')) {
      const customerId = this.storageService.getItem('customerId');
      if (customerId) {
        this.customerService.deleteCustomer(parseInt(customerId, 10)).subscribe(() => {
          console.log('Account deleted successfully');
          this.storageService.removeItem('customerId');
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
