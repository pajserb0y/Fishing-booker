import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FishingLessonsComponent } from './fishing-lessons.component';

describe('FishingLessonsComponent', () => {
  let component: FishingLessonsComponent;
  let fixture: ComponentFixture<FishingLessonsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FishingLessonsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FishingLessonsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
