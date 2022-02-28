import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BoatsComponent } from './boats/boats.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { FishingLessonsComponent } from './fishing-lessons/fishing-lessons.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegistrationPageComponent } from './registration-page/registration-page.component'; 
import { AuthGuard } from './service/auth.guard';
import { WeekendHousesComponent } from './weekend-houses/weekend-houses.component';
import { WeekendHouseProfileComponent } from './weekend-house-profile/weekend-house-profile.component';
import { CustomerFutureWeekendHouseReservationsComponent } from './customer-future-weekend-house-reservations/customer-future-weekend-house-reservations.component';
import { CustomerPastWeekendHouseReservationsComponent } from './customer-past-weekend-house-reservations/customer-past-weekend-house-reservations.component';
import { SpecialOffersComponent } from './special-offers/special-offers.component';
import { CustomerFutureBoatReservationsComponent } from './customer-future-boat-reservations/customer-future-boat-reservations.component';
import { CustomerPastBoatReservationsComponent } from './customer-past-boat-reservations/customer-past-boat-reservations.component';
import { CustomerPastFishingLessonReservationsComponent } from './customer-past-fishing-lesson-reservations/customer-past-fishing-lesson-reservations.component';
import { CustomerFutureFishingLessonReservationsComponent } from './customer-future-fishing-lesson-reservations/customer-future-fishing-lesson-reservations.component';
import { BoatProfileComponent } from './boat-profile/boat-profile.component';
import { ReportCustomersComponent } from './report-customers/report-customers.component';
import { CreateFishingLessonComponent } from './create-fishing-lesson/create-fishing-lesson.component';
import { CreateBoatComponent } from './create-boat/create-boat.component';
import { CreateWeekendHouseComponent } from './create-weekend-house/create-weekend-house.component';

const routes: Routes = [
  { path: '', component: LandingPageComponent },
  { path: 'register', component: RegistrationPageComponent },
  { path: 'weekend-houses', component: WeekendHousesComponent },
  { path: 'fishing-lessons', component: FishingLessonsComponent },
  { path: 'boats', component: BoatsComponent },
  { path: 'boat-profile', component: BoatProfileComponent },
  { path: 'login', component: LoginPageComponent },
  { path: 'user-profile', component: UserProfileComponent, canActivate: [AuthGuard], data: { role: ['ROLE_CUSTOMER', 'ROLE_INSTRUCTOR','ROLE_WEEKEND_HOUSE_OWNER','ROLE_BOAT_OWNER','ROLE_ADMIN']} },
  { path: 'customer-future-weekend-house-reservations', component: CustomerFutureWeekendHouseReservationsComponent, canActivate:[AuthGuard], data:{role : 'ROLE_CUSTOMER'} },
  { path: 'customer-past-weekend-house-reservations', component: CustomerPastWeekendHouseReservationsComponent, canActivate:[AuthGuard], data:{role : 'ROLE_CUSTOMER'} },
  { path: 'weekend-house-profile', component: WeekendHouseProfileComponent, canActivate:[AuthGuard], data:{role : 'ROLE_WEEKEND_HOUSE_OWNER'}},
  { path: 'special-offers', component: SpecialOffersComponent, canActivate:[AuthGuard], data:{role : 'ROLE_CUSTOMER'}},
  { path: 'customer-future-boat-reservations', component: CustomerFutureBoatReservationsComponent, canActivate:[AuthGuard], data:{role : 'ROLE_CUSTOMER'} },
  { path: 'customer-past-boat-reservations', component: CustomerPastBoatReservationsComponent, canActivate:[AuthGuard], data:{role : 'ROLE_CUSTOMER'} }, 
  { path: 'customer-future-fishing-lesson-reservations', component: CustomerFutureFishingLessonReservationsComponent, canActivate:[AuthGuard], data:{role : 'ROLE_CUSTOMER'} },
  { path: 'customer-past-fishing-lesson-reservations', component: CustomerPastFishingLessonReservationsComponent, canActivate:[AuthGuard], data:{role : 'ROLE_CUSTOMER'} },
  { path: 'report-customers', component: ReportCustomersComponent, canActivate: [AuthGuard], data: {role: ['ROLE_INSTRUCTOR', 'ROLE_WEEKEND_HOUSE_OWNER','ROLE_BOAT_OWNER']} },
  { path: 'create-fishing-lesson', component: CreateFishingLessonComponent, canActivate: [AuthGuard], data: { role: ['ROLE_INSTRUCTOR','ROLE_ADMIN']}},
  { path: 'create-boat', component: CreateBoatComponent, canActivate: [AuthGuard], data: { role: ['ROLE_BOAT_OWNER','ROLE_ADMIN']}},
  { path: 'create-weekend-house', component: CreateWeekendHouseComponent, canActivate: [AuthGuard], data: { role: ['ROLE_WEEKEND_HOUSE_OWNER','ROLE_ADMIN']}}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
