import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dollar-header',
  templateUrl: './dollar-header.component.html',
  styleUrl: './dollar-header.component.css'
})
export class DollarHeaderComponent {
  constructor(public router: Router) { }
}