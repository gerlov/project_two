import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginMainComponent } from './login-main/login-main.component';
import { LoginChooseComponent } from './login-choose/login-choose.component';
import { MittkontoComponent } from './mittkonto/mittkonto.component';
import { MinakvittonComponent } from './minakvitton/minakvitton.component';
import { HomeComponent } from './home/home.component';
import { RegistreraComponent } from './registrera/registrera.component';

const routes: Routes = [
  { path: '', component: LoginMainComponent }, // default route dvs ng serve will always start here 
  { path: 'loginchoose', component: LoginChooseComponent },
  { path: 'mittkonto', component: MittkontoComponent },
  { path: 'minakvitton', component: MinakvittonComponent },
  { path: 'home', component: HomeComponent }, 
  { path: 'registrera', component: RegistreraComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
