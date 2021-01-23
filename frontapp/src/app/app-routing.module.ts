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

const routes: Routes = [
  { path: 'loginform',
   component: LoginFormComponent },
  { path: 'pharmacy-list',
   component: PharmacyListComponent },
  { path: 'medicine-list',
   component: MedicineListComponent },
   { path: 'registerform',
   component: MedicineListComponent },
   { path: 'homepage',
  component: HomePageComponent },
  { path: 'myprofile',
  component: MyProfileComponent,
  canActivate: [GuardServiceService],
  data: { 
    expectedRole: 'ROLE_PATIENT'
  }   },{ path: 'myexaminationschedule',
  component: ExamScheduleComponent,
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
