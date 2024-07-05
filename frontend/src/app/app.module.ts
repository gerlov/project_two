import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { HttpClientModule, provideHttpClient, withFetch } from '@angular/common/http';
import { FormsModule } from '@angular/forms'; // forms

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { ImageLogoDollarComponent } from './image-logo-dollar/image-logo-dollar.component';
import { HeaderComponent } from './header/header.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { CentralborderComponent } from './centralborder/centralborder.component';
import { MittkontoComponent } from './mittkonto/mittkonto.component';
import { MinakvittonComponent } from './minakvitton/minakvitton.component';
import { LoginMainComponent } from './login-main/login-main.component';
import { LoginChooseComponent } from './login-choose/login-choose.component';
import { RegistreraComponent } from './registrera/registrera.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ImageLogoDollarComponent,
    HeaderComponent,
    NavbarComponent,
    FooterComponent,
    CentralborderComponent,
    MittkontoComponent,
    MinakvittonComponent,
    LoginMainComponent,
    LoginChooseComponent,
    RegistreraComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [
    provideClientHydration(),
    provideHttpClient(withFetch()) //  for http client to use fetch (ie from forms)
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
