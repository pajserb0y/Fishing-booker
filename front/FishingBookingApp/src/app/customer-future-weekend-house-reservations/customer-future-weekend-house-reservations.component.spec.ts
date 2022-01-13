import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerFutureWeekendHouseReservationsComponent } from './customer-future-weekend-house-reservations.component';

describe('CustomerFutureWeekendHouseReservationsComponent', () => {
  let component: CustomerFutureWeekendHouseReservationsComponent;
  let fixture: ComponentFixture<CustomerFutureWeekendHouseReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerFutureWeekendHouseReservationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerFutureWeekendHouseReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
