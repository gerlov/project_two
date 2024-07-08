import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registrera',
  templateUrl: './registrera.component.html',
  styleUrls: ['./registrera.component.css']
})
export class RegistreraComponent {
  name: string = '';
  email: string = '';
  password: string = '';

  constructor(private http: HttpClient, private router: Router) {}

  register() {
    this.http.post('http://localhost:8080/api/v1/customers/register', {
      name: this.name,
      email: this.email,
      password: this.password
    })
    .subscribe(response => {
      alert('Registrering lyckades!');
      this.router.navigate(['/loginchoose']); // Navigera till /loginchoose
    }, error => {
      console.error('Fel vid registrering:', error);
      alert('Registrering misslyckades. Vänligen försök igen.');
    });
  }
}
