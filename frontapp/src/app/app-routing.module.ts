import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { PharmacyListComponent } from './components/pharmacy-list/pharmacy-list.component';
import { MedicineListComponent } from './components/medicine-list/medicine-list.component';

const routes: Routes = [
  { path: 'loginform', component: LoginFormComponent },
  { path: 'pharmacy-list', component: PharmacyListComponent },
  { path: 'medicine-list', component: MedicineListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
