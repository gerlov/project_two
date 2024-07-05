import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login-choose',
  templateUrl: './login-choose.component.html',
  styleUrls: ['./login-choose.component.css']
})
export class LoginChooseComponent {
  email: string = '';
  password: string = '';

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    this.http.post('http://localhost:8080/api/v1/customers/login', {
      email: this.email,
      password: this.password
    }, { responseType: 'text' })
    .subscribe(response => {
      if (response === 'Login successful') {
        this.router.navigate(['/mittkonto']);
      } else {
        alert(response);
      }
    }, error => {
      console.error('Error:', error);
    });
  }

  register() {
    this.router.navigate(['/registrera']);
  }
}
