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
import { AngularFireModule } from '@angular/fire';
import { AngularFireDatabaseModule } from '@angular/fire/database';
import { environment } from '../environments/environment';
import { AngularFirestoreModule } from '@angular/fire/firestore';


@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    PharmacyListComponent,
    MedicineListComponent,
    RegisterFormComponent
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