import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dollar-header',
  templateUrl: './dollar-header.component.html',
  styleUrls: ['./dollar-header.component.css']
})
export class DollarHeaderComponent {
  constructor(public router: Router) { }

  toggleMenu(): void {
    const menu = document.getElementById('bottom-bar');
    if (menu) {
      menu.style.left = menu.style.left === '0px' ? '-100%' : '0px';
    }
  }
  
}  