import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';


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

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    this.http.post('http://localhost:8080/api/v1/customers/login', {
      email: this.email,
      password: this.password
    }, { responseType: 'text' })
    .subscribe(response => {
      if (response.match(/^\d+$/))
        {
        localStorage.setItem('customerId', response);
        this.router.navigate(['/account']);
      } else 
      {
        this.showUserNotFound = true;       
        this.userNotFoundMessage = `DollarStore: ${response}`;     // 
      }
    }, error => {
      console.error('Error:', error);
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