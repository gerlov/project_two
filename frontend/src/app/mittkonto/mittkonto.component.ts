import { Component, OnInit } from '@angular/core';
import { CustomerService, Customer } from '../customer.service';
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
  showConfirmDialog: boolean = false;
  showFeedbackDialog: boolean = false; 
  showFeedbackConfirmationDialog: boolean = false;
  feedbackText: string = ''; // when 'Other' is chosen
  selectedFeedback: string | null = null;
  showOtherTextArea: boolean = false;

  constructor(
    private customerService: CustomerService,
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
          this.refreshPage();
        }, error => {
          console.error('Error:', error);
        });
      }
    } else {
      this.isEditing = true;
    }
  }

  refreshPage(): void {
    window.location.reload(); 
  }

  confirmDeleteAccount(): void {
    console.log('Confirm delete account called');
    this.showConfirmDialog = true;
  }

  deleteAccount(): void {
    console.log('Delete account called');
    this.showConfirmDialog = false;
    const customerId = this.storageService.getItem('customerId');
    if (customerId) {
      this.customerService.deleteCustomer(parseInt(customerId, 10)).subscribe(() => {
        console.log('Account deleted successfully');
        this.storageService.removeItem('customerId');
        this.showFeedbackConfirmationDialog = true;
      }, error => {
        console.error('Error deleting account:', error);
      });
    }
  }

  submitFeedback(): void {
    if (!this.selectedFeedback && !this.feedbackText) {
      alert('Please select an option or specify "Other".');
      return;
    }

    console.log('Feedback submitted:', this.selectedFeedback, this.feedbackText);
    // Here, you would send the feedback to the server or handle it as needed.
    this.showFeedbackDialog = false;
    this.router.navigate(['/loginchoose']);
  }

  cancelFeedback(): void {
    this.showFeedbackDialog = false;
    this.router.navigate(['/loginchoose']);
  }

  closeFeedbackDialog(): void {
    this.showFeedbackDialog = false;
    this.router.navigate(['/loginchoose']); // Always navigate to /loginchoose so not still inside dashboard
  }

  navigateToLogin(): void {
    this.router.navigate(['/loginchoose']);
  }

  onFeedbackChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.checked) {
      this.selectedFeedback = input.value;
      this.showOtherTextArea = input.value === 'OTHER';
    } else {
      if (input.value === 'OTHER') {
        this.showOtherTextArea = false;
        this.feedbackText = ''; 
      }
      this.selectedFeedback = null;
    }
  }
}