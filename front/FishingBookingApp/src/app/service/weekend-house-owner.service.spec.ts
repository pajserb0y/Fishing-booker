import { TestBed } from '@angular/core/testing';

import { WeekendHouseOwnerService } from './weekend-house-owner.service';

describe('WeekendHouseOwnerService', () => {
  let service: WeekendHouseOwnerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WeekendHouseOwnerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
