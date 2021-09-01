import { TestBed } from '@angular/core/testing';

import { QuarterReportServiceService } from './quarter-report-service.service';

describe('QuarterReportServiceService', () => {
  let service: QuarterReportServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(QuarterReportServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
