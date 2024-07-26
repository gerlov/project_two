import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router'; // Import Router

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent {
  email: string = '';
  token: string = '';
  newPassword: string = '';
  confirmPassword: string = '';
  passwordChanged: boolean = false;
  errorMessage: string = ''; // Single error message variable

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    this.errorMessage = ''; // Clear previous error message

    this.http.post<any>('http://localhost:8080/api/v1/customers/reset-password', { email: this.email })
      .subscribe(
        response => {
          console.log('Password reset email sent successfully');
          this.passwordChanged = true;
        },
        error => {
          console.error('Error sending password reset email:', error);
          this.errorMessage = 'Failed to send password reset email'; // Single error message for the email form
        }
      );
  }

  onPasswordReset() {
    this.errorMessage = ''; // Clear previous error message

    if (this.newPassword !== this.confirmPassword) {
      this.errorMessage = 'Passwords do not match.'; // Error for password mismatch
      return;
    }

    const requestBody = {
      token: this.token,
      password: this.newPassword
    };

    this.http.post<any>('http://localhost:8080/api/v1/customers/change-password', requestBody)
      .subscribe(
        response => {
          console.log('Password changed successfully');
          this.router.navigate(['/loginchoose']);
        },
        error => {
          console.error('Error changing password:', error);
          if (error.status === 400) {
            this.errorMessage = error.error.error || 'Weak password or token invalid.'; // Handle weak password or token invalid
          } else if (error.status === 404) {
            this.errorMessage = 'Invalid reset token.'; // Handle invalid token
          } else {
            this.errorMessage = 'An unexpected error occurred.'; // Handle unexpected errors
          }
        }
      );
  }
}
