import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { PharmacyListComponent } from './components/pharmacy-list/pharmacy-list.component';
import { MedicineListComponent } from './components/medicine-list/medicine-list.component';
import { HomePageComponent } from './components/home-page/home-page.component';

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
  {
    path: '',
    redirectTo: 'homepage',
    pathMatch: 'full'
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
