import { TestBed, inject } from '@angular/core/testing';

import { JrpService } from './jrp.service';

describe('JrpService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [JrpService]
    });
  });

  it('should be created', inject([JrpService], (service: JrpService) => {
    expect(service).toBeTruthy();
  }));
});
