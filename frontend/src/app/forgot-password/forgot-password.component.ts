import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent {
  email: string = '';
  token: string = '';
  newPassword: string = '';
  passwordChanged: boolean = false;

  constructor(private http: HttpClient) {}

  onSubmit() {
    // Send HTTP request to send password reset email
    this.http.post<any>('http://localhost:8080/api/v1/customers/reset-password', { email: this.email })
      .subscribe(
        response => {
          console.log('Password reset email sent successfully');
          this.passwordChanged = true; // Show the second form for entering token and new password
        },
        error => {
          console.error('Error sending password reset email:', error);
          // Handle error, show error message to user
        }
      );
  }

  onPasswordReset() {
    const requestBody = {
      token: this.token,
      password: this.newPassword
    };

    this.http.post<any>('http://localhost:8080/api/v1/customers/change-password', requestBody)
      .subscribe(
        response => {
          console.log('Password changed successfully');
          // Optionally, redirect to login page or show success message
        },
        error => {
          console.error('Error changing password:', error);
          // Handle error, show error message to user
        }
      );
  }
}
