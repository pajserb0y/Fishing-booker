import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BoatsComponent } from './boats/boats.component';
import { CustomerProfileComponent } from './customer-profile/customer-profile.component';
import { FishingLessonsComponent } from './fishing-lessons/fishing-lessons.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegistrationPageComponent } from './registration-page/registration-page.component'; 
import { AuthGuard } from './service/auth.guard';
import { WeekendHousesComponent } from './weekend-houses/weekend-houses.component';

const routes: Routes = [
  { path: '', component: LandingPageComponent },
  { path: 'register', component: RegistrationPageComponent },
  { path: 'weekend-houses', component: WeekendHousesComponent },
  { path: 'fishing-lessons', component: FishingLessonsComponent },
  { path: 'boats', component: BoatsComponent },
  { path: 'login', component: LoginPageComponent },
  { path: 'customer-profile', component: CustomerProfileComponent, canActivate: [AuthGuard], 
                              data: { role: ['ROLE_CUSTOMER', 'ROLE_INSTRUCTOR','ROLE_WEEKEND_HOUSE_OWNER','ROLE_BOAT_OWNER','ROLE_ADMIN']} },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
