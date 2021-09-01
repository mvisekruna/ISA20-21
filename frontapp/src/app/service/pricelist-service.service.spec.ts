import { TestBed } from '@angular/core/testing';

import { PricelistServiceService } from './pricelist-service.service';

describe('PricelistServiceService', () => {
  let service: PricelistServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PricelistServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
