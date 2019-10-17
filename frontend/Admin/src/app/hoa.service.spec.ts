import { TestBed } from '@angular/core/testing';

import { HOAService } from './hoa.service';

describe('HOAService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: HOAService = TestBed.get(HOAService);
    expect(service).toBeTruthy();
  });
});
