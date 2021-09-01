import { TestBed } from '@angular/core/testing';

import { MonthlyReportServiceService } from './monthly-report-service.service';

describe('MonthlyReportServiceService', () => {
  let service: MonthlyReportServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MonthlyReportServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
