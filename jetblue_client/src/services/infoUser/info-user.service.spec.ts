import { TestBed } from '@angular/core/testing';

import { InfoUserService } from './info-user.service';

describe('InfoUserService', () => {
  let service: InfoUserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InfoUserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
