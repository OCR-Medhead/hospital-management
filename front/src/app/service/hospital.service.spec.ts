import { TestBed } from '@angular/core/testing';

import { HospitalService } from './hospital.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('HospitalService', () => {
  let service: HospitalService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(HospitalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

});
