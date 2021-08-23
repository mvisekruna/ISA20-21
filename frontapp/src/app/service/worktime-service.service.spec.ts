import { TestBed } from '@angular/core/testing';

import { WorktimeServiceService } from './worktime-service.service';

describe('WorktimeServiceService', () => {
  let service: WorktimeServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WorktimeServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
