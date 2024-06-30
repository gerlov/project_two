import { Component } from '@angular/core';
import { AxiosService } from '../axios.service';


@Component({
  selector: 'app-auth-content',
  standalone: true,
  imports: [],
  templateUrl: './auth-content.component.html',
  styleUrl: './auth-content.component.css'
})
export class AuthContentComponent {

  data: string[] = [];
  constructor(private AxiosService: AxiosService){

  }

  ngOnInit(): void{
    this.AxiosService.request(
      "GET",
      "/api/v1/customers",
      {}
    ).then(
      (response) => this.data = response.data
    );

  }

}
