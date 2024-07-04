import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';



import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { ImageLogoDollarComponent } from './image-logo-dollar/image-logo-dollar.component';
import { HeaderComponent } from './header/header.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { CentralborderComponent } from './centralborder/centralborder.component';
import { MittkontoComponent } from './mittkonto/mittkonto.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ImageLogoDollarComponent,
    HeaderComponent,
    NavbarComponent,
    FooterComponent,
    CentralborderComponent,
    MittkontoComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
