import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerFutureFishingLessonReservationsComponent } from './customer-future-fishing-lesson-reservations.component';

describe('CustomerFutureFishingLessonReservationsComponent', () => {
  let component: CustomerFutureFishingLessonReservationsComponent;
  let fixture: ComponentFixture<CustomerFutureFishingLessonReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerFutureFishingLessonReservationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerFutureFishingLessonReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
