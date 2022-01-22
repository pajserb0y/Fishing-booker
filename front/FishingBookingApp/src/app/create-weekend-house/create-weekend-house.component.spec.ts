import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateWeekendHouseComponent } from './create-weekend-house.component';

describe('CreateWeekendHouseComponent', () => {
  let component: CreateWeekendHouseComponent;
  let fixture: ComponentFixture<CreateWeekendHouseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateWeekendHouseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateWeekendHouseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
