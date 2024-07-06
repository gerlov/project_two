import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginMainComponent } from './login-main/login-main.component';
import { LoginChooseComponent } from './login-choose/login-choose.component';
import { MittkontoComponent } from './mittkonto/mittkonto.component';
import { MinakvittonComponent } from './minakvitton/minakvitton.component';
import { RegistreraComponent } from './registrera/registrera.component';
import { AccountpageComponent } from './accountpage/accountpage.component';

const routes: Routes = [
  { path: '', component: LoginMainComponent }, // default route dvs ng serve will always start here
  { path: 'loginchoose', component: LoginChooseComponent },
  { path: 'registrera', component: RegistreraComponent },
  { path: 'account', component: AccountpageComponent, children: [
    { path: 'mittkonto', component: MittkontoComponent },
    { path: 'minakvitton', component: MinakvittonComponent },
    { path: '', redirectTo: 'mittkonto', pathMatch: 'full' },
  ]},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
