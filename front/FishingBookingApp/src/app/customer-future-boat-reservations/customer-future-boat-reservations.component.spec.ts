import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerFutureBoatReservationsComponent } from './customer-future-boat-reservations.component';

describe('CustomerFutureBoatReservationsComponent', () => {
  let component: CustomerFutureBoatReservationsComponent;
  let fixture: ComponentFixture<CustomerFutureBoatReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerFutureBoatReservationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerFutureBoatReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
