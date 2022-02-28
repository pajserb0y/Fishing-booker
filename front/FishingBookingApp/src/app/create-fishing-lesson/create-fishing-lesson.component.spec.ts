import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateFishingLessonComponent } from './create-fishing-lesson.component';

describe('CreateFishingLessonComponent', () => {
  let component: CreateFishingLessonComponent;
  let fixture: ComponentFixture<CreateFishingLessonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateFishingLessonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateFishingLessonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
