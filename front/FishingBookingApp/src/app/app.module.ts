import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import {MatTableModule} from '@angular/material/table';
import { MatSliderModule } from '@angular/material/slider';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule  } from '@angular/material/icon';
import { MatGridListModule} from '@angular/material/grid-list';
import { MatCardModule} from '@angular/material/card';
import { MatBadgeModule} from '@angular/material/badge';
import { MatDialogModule} from '@angular/material/dialog';
import { MatFormFieldModule} from '@angular/material/form-field';
import { MatInputModule} from '@angular/material/input';
import { MatCheckboxModule} from '@angular/material/checkbox';
import { MatStepperModule} from '@angular/material/stepper';
import { MatRadioModule} from '@angular/material/radio';
import { MatDividerModule} from '@angular/material/divider';
import { MatListModule} from '@angular/material/list';
import { MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatSelectModule} from '@angular/material/select';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatTabsModule} from '@angular/material/tabs';
import {MatMenuModule} from '@angular/material/menu';
import {MatPaginatorModule} from '@angular/material/paginator';
import { MatCarouselModule } from 'ng-mat-carousel';


import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { RegistrationPageComponent } from './registration-page/registration-page.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { CustomerService } from './service/customer.service';
import { BoatsComponent } from './boats/boats.component';
import { WeekendHousesComponent } from './weekend-houses/weekend-houses.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { FishingLessonsComponent } from './fishing-lessons/fishing-lessons.component';
import { CustomerProfileComponent } from './customer-profile/customer-profile.component';

const MaterialComponents = [
  MatSliderModule,
  MatCarouselModule,
  MatToolbarModule,
  MatButtonModule,
  MatIconModule,
  MatGridListModule,
  MatCardModule,
  MatBadgeModule,
  MatDialogModule,
  MatFormFieldModule,
  MatInputModule,
  MatCheckboxModule,
  MatStepperModule,
  MatRadioModule,
  MatDividerModule,
  MatListModule,
  MatDatepickerModule,
  MatNativeDateModule,
  MatSelectModule,
  MatSnackBarModule,
  MatTabsModule,
  MatMenuModule,
  MatTableModule,
  MatPaginatorModule
];

@NgModule({
  declarations: [
    AppComponent,
    LandingPageComponent,
    HeaderComponent, 
    FooterComponent,
    RegistrationPageComponent,
    BoatsComponent,
    WeekendHousesComponent,
    LoginPageComponent,
    FishingLessonsComponent,
    CustomerProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialComponents,
    HttpClientModule,
    FormsModule,
    MatButtonModule,
    ReactiveFormsModule
    
  ],
  providers: [CustomerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
