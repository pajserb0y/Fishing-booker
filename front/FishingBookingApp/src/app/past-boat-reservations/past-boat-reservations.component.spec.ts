import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PastBoatReservationsComponent } from './past-boat-reservations.component';

describe('PastBoatReservationsComponent', () => {
  let component: PastBoatReservationsComponent;
  let fixture: ComponentFixture<PastBoatReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PastBoatReservationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PastBoatReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
