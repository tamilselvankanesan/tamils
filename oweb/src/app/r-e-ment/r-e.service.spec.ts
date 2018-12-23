import { TestBed, inject } from '@angular/core/testing';

import { REService } from './r-e.service';

describe('REServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [REService]
    });
  });

  it('should be created', inject([REService], (service: REService) => {
    expect(service).toBeTruthy();
  }));
});
