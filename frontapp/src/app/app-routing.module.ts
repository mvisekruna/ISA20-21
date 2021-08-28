import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { PharmacyListComponent } from './components/pharmacy-list/pharmacy-list.component';
import { MedicineListComponent } from './components/medicine-list/medicine-list.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { HomePagePatientComponent } from './components/home-page/home-page-patient/home-page-patient.component';
import { MyProfileComponent } from './components/my-profile/my-profile.component';
import { GuardServiceService } from './service/guard-service.service';
import { ExamScheduleComponent } from './components/exam-schedule/exam-schedule.component';
import { CounselingScheduleComponent } from './components/counseling-schedule/counseling-schedule.component';
import { SchedulingCounselingComponent } from './components/scheduling-counseling/scheduling-counseling.component';
import { MedicineReservationRequest } from './model/medicine-reservation-request';
import { MedicineReservationListComponent } from './components/medicine-reservation-list/medicine-reservation-list.component';
import { ExaminationHistoryListComponent } from './components/examination-history-list/examination-history-list.component';
import { CounselingHistoryListComponent } from './components/counseling-history-list/counseling-history-list.component';
import { RegisterFormComponent } from './components/register-form/register-form.component';
import { CreatePharmacyComponent } from './components/create-pharmacy/create-pharmacy.component';
import { RegisterNewUserComponent } from './components/register-new-user/register-new-user.component';
import { CreateMedicineComponent } from './components/create-medicine/create-medicine.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { WriteComplaintComponent } from './components/write-complaint/write-complaint.component';
import { ComplaintListComponent } from './components/complaint-list/complaint-list.component';
import { PatientListComponent } from './components/patient-list/patient-list.component';
import { WorkingScheduleComponent } from './components/working-schedule/working-schedule.component';
import { PharmacistsListComponent } from './components/pharmacists-list/pharmacists-list.component';
import { AddNewPharmacistOrDermatologistComponent } from './components/add-new-pharmacist-or-dermatologist/add-new-pharmacist-or-dermatologist.component';
import { AskForAbsenceComponent } from './components/ask-for-absence/ask-for-absence.component';
import { AbsencesListComponent } from './components/absences-list/absences-list.component';
import { AddMedicineToPharmacyComponent } from './components/add-medicine-to-pharmacy/add-medicine-to-pharmacy.component';
import { AddPromotionComponent } from './components/add-promotion/add-promotion.component';
import { AddOrderItemComponent } from './components/add-order-item/add-order-item.component';

const routes: Routes = [
  { path: 'loginform',
   component: LoginFormComponent },
   { path: 'pharmacy-list',
   component: PharmacyListComponent,
   canActivate: [GuardServiceService],
   data: { 
     expectedRole: 'ROLE_PATIENT ROLE_PHARMACY_ADMIN ROLE_SYSTEM_ADMIN UNAUTHENTICATED'
   }   },
   {
     path : 'add-new',
     component : AddNewPharmacistOrDermatologistComponent,
     canActivate: [GuardServiceService],
     data: { 
      expectedRole: 'ROLE_PHARMACY_ADMIN'
    }
   },
   { path: 'changepassword',
   component: ChangePasswordComponent },
  { path: 'medicine-list',
   component: MedicineListComponent },
   { path: 'registerform',
   component: RegisterFormComponent },
   { path: 'homepage',
  component: HomePageComponent },
  { path: 'myprofile',
  component: MyProfileComponent,
  canActivate: [GuardServiceService],
  data: { 
    expectedRole: 'ROLE_PATIENT ROLE_DERMATOLOGIST ROLE_PHARMACIST ROLE_PHARMACY_ADMIN'
  }   },
  { path: 'patientlist',
  component: PatientListComponent,
  canActivate: [GuardServiceService],
  data: { 
    expectedRole: 'ROLE_DERMATOLOGIST ROLE_PHARMACIST'
  }   },
//moje
  { path: 'pharmacists-list',
  component: PharmacistsListComponent,
  canActivate: [GuardServiceService],
  data: { 
    expectedRole: 'ROLE_PHARMACY_ADMIN'
  }   },
  { path: 'ask-for-absence',
  component : AskForAbsenceComponent,
  canActivate: [GuardServiceService],
  data: {
    expectedRole: 'ROLE_PHARMACIST ROLE_DERMATOLOGIST'
  }  },
  { path: 'absences-list', 
  component: AbsencesListComponent,
  canActivate: [GuardServiceService],
  data: {
    expectedRole: 'ROLE_PHARMACY_ADMIN ROLE_SYSTEM_ADMIN'
  } },
  {
    path: 'add-medicine-to-pharmacy',
    component: AddMedicineToPharmacyComponent,
    canActivate: [GuardServiceService],
    data: {
      expectedRole: 'ROLE_PHARMACY_ADMIN'
    } },
  {
    path: 'add-promotion',
    component: AddPromotionComponent,
    canActivate: [GuardServiceService],
    data: {
      expectedRole: 'ROLE_PHARMACY_ADMIN'
    } },
  {
    path: 'add-order-item',
    component: AddOrderItemComponent,
    canActivate: [GuardServiceService],
    data: {
      expectedRole: 'ROLE_PHARMACY_ADMIN'
    } },
//
  { path: 'workingschedule',
  component: WorkingScheduleComponent,
  canActivate: [GuardServiceService],
  data: { 
    expectedRole: 'ROLE_DERMATOLOGIST ROLE_PHARMACIST'
  }   },
  { path: 'writecomplaint',
  component: WriteComplaintComponent,
  canActivate: [GuardServiceService],
  data: { 
    expectedRole: 'ROLE_PATIENT'
  }   },
  { path: 'myexaminationschedule',
  component: ExamScheduleComponent,
  canActivate: [GuardServiceService],
  data: { 
    expectedRole: 'ROLE_PATIENT'
  }   },
  { path: 'mycounselingschedule',
  component: CounselingScheduleComponent,
  canActivate: [GuardServiceService],
  data: { 
    expectedRole: 'ROLE_PATIENT'
  }   },
  { path: 'schedulingcounseling',
  component: SchedulingCounselingComponent,
  canActivate: [GuardServiceService],
  data: { 
    expectedRole: 'ROLE_PATIENT'
  }   },
  { path: 'complaintlist',
  component: ComplaintListComponent,
  canActivate: [GuardServiceService],
  data: { 
    expectedRole: 'ROLE_SYSTEM_ADMIN'
  }   },
  { path: 'createpharmacy',
  component: CreatePharmacyComponent,
  canActivate: [GuardServiceService],
  data: { 
    expectedRole: 'ROLE_SYSTEM_ADMIN'
  }   },
  { path: 'createmedicine',
  component: CreateMedicineComponent,
  canActivate: [GuardServiceService],
  data: { 
    expectedRole: 'ROLE_SYSTEM_ADMIN'
  }   },
  { path: 'registernewuser',
  component: RegisterNewUserComponent,
  canActivate: [GuardServiceService],
  data: { 
    expectedRole: 'ROLE_SYSTEM_ADMIN'
  }   },
  { path: 'medicinereservationlist',
  component: MedicineReservationListComponent,
  canActivate: [GuardServiceService],
  data: { 
    expectedRole: 'ROLE_PATIENT'
  }   },
  { path: 'examhistorylist',
  component: ExaminationHistoryListComponent,
  canActivate: [GuardServiceService],
  data: { 
    expectedRole: 'ROLE_PATIENT'
  }   },
  { path: 'counhistorylist',
  component: CounselingHistoryListComponent,
  canActivate: [GuardServiceService],
  data: { 
    expectedRole: 'ROLE_PATIENT'
  }   },
  {
    path: '**',
    redirectTo: 'homepage',
    pathMatch: 'full'
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
