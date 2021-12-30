import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WeekendHousesComponent } from './weekend-houses.component';

describe('WeekendHousesComponent', () => {
  let component: WeekendHousesComponent;
  let fixture: ComponentFixture<WeekendHousesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WeekendHousesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WeekendHousesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
