import { BrowserModule } from '@angular/platform-browser';
import { Injectable, NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { PharmacyListComponent } from './components/pharmacy-list/pharmacy-list.component';
import { PharmacyServiceService } from './service/pharmacy-service.service';
import { HttpClientModule, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MedicineListComponent } from './components/medicine-list/medicine-list.component';
import { MedicineServiceService } from './service/medicine-service.service';
import { RegisterFormComponent } from './components/register-form/register-form.component';
import { AuthServiceService } from './service/auth-service.service';
import { ApiServiceService } from './service/api-service.service';
import { UserServiceService } from './service/user-service.service';
import { CommonModule } from '@angular/common';
import { HomePageComponent } from './components/home-page/home-page.component';
import { HomePagePatientComponent } from './components/home-page/home-page-patient/home-page-patient.component';
import { MyProfileComponent } from './components/my-profile/my-profile.component';
import { JwtHelperService, JWT_OPTIONS } from '@auth0/angular-jwt';
import { AllergiesListComponent } from './components/allergies-list/allergies-list.component';
import { PharmacyProfileComponent } from './components/pharmacy-profile/pharmacy-profile.component';
import { ExamScheduleComponent } from './components/exam-schedule/exam-schedule.component';
import { CounselingScheduleComponent } from './components/counseling-schedule/counseling-schedule.component';
import { SchedulingCounselingComponent } from './components/scheduling-counseling/scheduling-counseling.component';
import { MedicineReservationListComponent } from './components/medicine-reservation-list/medicine-reservation-list.component';
import { ExaminationHistoryListComponent } from './components/examination-history-list/examination-history-list.component';
import { CounselingHistoryListComponent } from './components/counseling-history-list/counseling-history-list.component';
import { SearchPipe } from './search.pipe';
import { DataTablesModule } from 'angular-datatables';
import { HomePageSystemAdminComponent } from './components/home-page/home-page-system-admin/home-page-system-admin.component';
import { CreatePharmacyComponent } from './components/create-pharmacy/create-pharmacy.component';
import { RegisterNewUserComponent } from './components/register-new-user/register-new-user.component';
import { CreateMedicineComponent } from './components/create-medicine/create-medicine.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { WriteComplaintComponent } from './components/write-complaint/write-complaint.component';
import { ComplaintListComponent } from './components/complaint-list/complaint-list.component';
import { HomePageDermatologistComponent } from './components/home-page/home-page-dermatologist/home-page-dermatologist.component';
import { HomePagePharmacistComponent } from './components/home-page/home-page-pharmacist/home-page-pharmacist.component';
import { PatientListComponent } from './components/patient-list/patient-list.component';
import { WorkingScheduleComponent } from './components/working-schedule/working-schedule.component';
import { PharmacistsListComponent } from './components/pharmacists-list/pharmacists-list.component';
import { HomePagePharmacyAdminComponent } from './components/home-page/home-page-pharmacy-admin/home-page-pharmacy-admin.component';
import { AddNewPharmacistOrDermatologistComponent } from './components/add-new-pharmacist-or-dermatologist/add-new-pharmacist-or-dermatologist.component';

import { AgmCoreModule } from '@agm/core';
import { AskForAbsenceComponent } from './components/ask-for-absence/ask-for-absence.component';
import { AbsencesListComponent } from './components/absences-list/absences-list.component';
import { AddMedicineToPharmacyComponent } from './components/add-medicine-to-pharmacy/add-medicine-to-pharmacy.component';
import { AddPromotionComponent } from './components/add-promotion/add-promotion.component';
import { AddOrderItemComponent } from './components/add-order-item/add-order-item.component';
import { AddNewOrderComponent } from './components/add-new-order/add-new-order.component';
import { AddNewOfferComponent } from './components/add-new-offer/add-new-offer.component';
import { HomePageSupplierComponent } from './components/home-page/home-page-supplier/home-page-supplier.component';
import { OfferListComponent } from './components/offer-list/offer-list.component';
import { AddPricelistComponent } from './components/add-pricelist/add-pricelist.component';
import { MonthlyReportComponent } from './components/monthly-report/monthly-report.component';
import { QuarterReportComponent } from './components/quarter-report/quarter-report.component';
import { PricelistListComponent } from './components/pricelist-list/pricelist-list.component';
import { MedicineUpdateComponent } from './components/medicine-update/medicine-update.component';
import { AddFreeExaminationComponent } from './components/add-free-examination/add-free-examination.component';

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
    AllergiesListComponent,
    PharmacyProfileComponent,
    ExamScheduleComponent,
    CounselingScheduleComponent,
    SchedulingCounselingComponent,
    MedicineReservationListComponent,
    ExaminationHistoryListComponent,
    CounselingHistoryListComponent,
    SearchPipe,
    HomePageSystemAdminComponent,
    CreatePharmacyComponent,
    RegisterNewUserComponent,
    CreateMedicineComponent,
    ChangePasswordComponent,
    WriteComplaintComponent,
    ComplaintListComponent,
    HomePageDermatologistComponent,
    HomePagePharmacistComponent,
    PatientListComponent,
    WorkingScheduleComponent,
    PharmacistsListComponent,
    HomePagePharmacyAdminComponent,
    AddNewPharmacistOrDermatologistComponent,
    AskForAbsenceComponent,
    AbsencesListComponent,
    AddMedicineToPharmacyComponent,
    AddPromotionComponent,
    AddOrderItemComponent,
    AddNewOrderComponent,
    AddNewOfferComponent,
    HomePageSupplierComponent,
    OfferListComponent,
    AddPricelistComponent,
  
    MonthlyReportComponent,
    QuarterReportComponent,
    PricelistListComponent,
    MedicineUpdateComponent,
    AddFreeExaminationComponent,
   
  ],
  imports: [
    DataTablesModule,
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDMB95HndoUh1HWZJ0v2MFyWWQ-eaRWVGw'
    })
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