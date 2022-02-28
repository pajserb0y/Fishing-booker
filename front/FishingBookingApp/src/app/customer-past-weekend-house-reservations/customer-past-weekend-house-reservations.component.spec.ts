import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerPastWeekendHouseReservationsComponent } from './customer-past-weekend-house-reservations.component';

describe('CustomerPastWeekendHouseReservationsComponent', () => {
  let component: CustomerPastWeekendHouseReservationsComponent;
  let fixture: ComponentFixture<CustomerPastWeekendHouseReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerPastWeekendHouseReservationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerPastWeekendHouseReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
