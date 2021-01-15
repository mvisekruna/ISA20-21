import { BrowserModule } from '@angular/platform-browser';
import { Injectable, NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { PharmacyListComponent } from './components/pharmacy-list/pharmacy-list.component';
import { PharmacyServiceService } from './service/pharmacy-service.service';
import { HttpClientModule, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MedicineListComponent } from './components/medicine-list/medicine-list.component';
import { MedicineServiceService } from './service/medicine-service.service';
import { RegisterFormComponent } from './components/register-form/register-form.component';
import { AuthServiceService } from './service/auth-service.service';
import { ApiServiceService } from './service/api-service.service';
import { UserServiceService } from './service/user-service.service';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';  
import { HomePageComponent } from './components/home-page/home-page.component';
import { HomePagePatientComponent } from './components/home-page/home-page-patient/home-page-patient.component';
import { MyProfileComponent } from './components/my-profile/my-profile.component';
import { JwtHelperService, JWT_OPTIONS } from '@auth0/angular-jwt';
import { AllergiesListComponent } from './components/allergies-list/allergies-list.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    PharmacyListComponent,
    MedicineListComponent,
    RegisterFormComponent,
    HomePageComponent,
    HomePagePatientComponent,
    MyProfileComponent,
    AllergiesListComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [PharmacyServiceService,
    MedicineServiceService, 
    AuthServiceService,
    UserServiceService,
    JwtHelperService,
    { provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
    ApiServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
@Injectable(
)
export class XhrInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const xhr = req.clone({
      headers: req.headers.set('X-Requested-With', 'XMLHttpRequest')
    });
    return next.handle(xhr);
  }
}