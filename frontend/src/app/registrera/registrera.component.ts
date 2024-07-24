import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-registrera',
  templateUrl: './registrera.component.html',
  styleUrls: ['./registrera.component.css']
})
export class RegistreraComponent {
  name: string = '';
  email: string = '';
  password: string = '';
  confirmPassword: string = '';
  passwordMismatch: boolean = false;

  constructor(private customerService: CustomerService, private router: Router) {}

  register() {
    if (this.password !== this.confirmPassword) {
      this.passwordMismatch = true;
      return;
    }

    this.passwordMismatch = false;

    this.customerService.registerCustomer({
      name: this.name,
      email: this.email,
      password: this.password,
    })
    .subscribe(response => {
      alert('Registrering lyckades!');
      this.router.navigate(['/loginchoose']); // Navigera till /loginchoose
    }, error => {
      if (error.status === 409) {
        alert('Email already taken. Please use a different email.');
      } else {
        console.error('Registration failed:', error);
        alert('Registration failed. Please try again.');
      }
    });
  }
}
