import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-dollar-header',
  templateUrl: './dollar-header.component.html',
  styleUrl: './dollar-header.component.css'
})
export class DollarHeaderComponent {
  constructor(public router: Router, public authService: AuthService) { }

  logout() {
    this.authService.logout();
  } 

  toggleMenu(): void {
    const menu = document.getElementById('bottom-bar');
    if (menu) {
      menu.style.left = menu.style.left === '0px' ? '-100%' : '0px';
    }
  } 
}
