import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { StorageService } from '../storage.service';


@Component({
  selector: 'app-login-choose',
  templateUrl: './login-choose.component.html',
  styleUrls: ['./login-choose.component.css']
})
export class LoginChooseComponent {
  email: string = '';
  password: string = '';
  showUserNotFound: boolean = false; 
  userNotFoundMessage: string = '';  
  isSubmitting: boolean = false;       

  constructor(private http: HttpClient, private router: Router, private storageService: StorageService) {}

  onSubmit() {

    this.isSubmitting = true; // disable login btn once clicked

    this.http.post('http://localhost:8080/api/v1/customers/login', {
      email: this.email,
      password: this.password
    }, { responseType: 'text' })
    .subscribe(response => {
      if (response.match(/^\d+$/)) {
        this.storageService.setItem('customerId', response); 
        this.router.navigate(['/account']);
      } else {
        this.showUserNotFound = true;
        this.userNotFoundMessage = `DollarStore: ${response}`;
      }
      setTimeout(() => {
        this.isSubmitting = false;  // re-enable login btn after 2 (to test) sec 
        console.log('5 sec after click. Button now enabled.'); 
      }, 5000);                     // adjust delay here 

    }, error => {
      console.error('Error:', error); 
      this.isSubmitting = false;    // re-enable login btn if returned login error  
      console.log('Error occurred. Button now enabled.');
    });
  }


  onDismiss() {
    this.showUserNotFound = false;
    this.email = '';
    this.password = '';
  }

  register() {
    this.router.navigate(['/registrera']);
  }
}
