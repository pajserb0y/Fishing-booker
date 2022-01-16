import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerPastFishingLessonReservationsComponent } from './customer-past-fishing-lesson-reservations.component';

describe('CustomerPastFishingLessonReservationsComponent', () => {
  let component: CustomerPastFishingLessonReservationsComponent;
  let fixture: ComponentFixture<CustomerPastFishingLessonReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerPastFishingLessonReservationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerPastFishingLessonReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
