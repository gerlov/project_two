import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MittkontoComponent } from './mittkonto/mittkonto.component';
import { MinakvittonComponent } from './minakvitton/minakvitton.component';

const routes: Routes = [
  { path: 'mittkonto', component: MittkontoComponent },
  { path: 'minakvitton', component: MinakvittonComponent },
  { path: '', redirectTo: '/mittkonto', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
