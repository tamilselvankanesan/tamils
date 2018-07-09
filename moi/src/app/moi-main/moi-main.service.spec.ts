import { TestBed, inject } from '@angular/core/testing';

import { MoiMainService } from './moi-main.service';

describe('MoiMainService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MoiMainService]
    });
  });

  it('should be created', inject([MoiMainService], (service: MoiMainService) => {
    expect(service).toBeTruthy();
  }));
});
