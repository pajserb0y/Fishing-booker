import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerPastBoatReservationsComponent } from './customer-past-boat-reservations.component';

describe('CustomerPastBoatReservationsComponent', () => {
  let component: CustomerPastBoatReservationsComponent;
  let fixture: ComponentFixture<CustomerPastBoatReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerPastBoatReservationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerPastBoatReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
