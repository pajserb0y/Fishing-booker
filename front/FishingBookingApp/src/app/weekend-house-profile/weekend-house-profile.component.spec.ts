import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WeekendHouseProfileComponent } from './weekend-house-profile.component';

describe('WeekendHouseProfileComponent', () => {
  let component: WeekendHouseProfileComponent;
  let fixture: ComponentFixture<WeekendHouseProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WeekendHouseProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WeekendHouseProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
