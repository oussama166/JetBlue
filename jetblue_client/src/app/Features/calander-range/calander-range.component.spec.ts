import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalanderRangeComponent } from './calander-range.component';

describe('CalanderRangeComponent', () => {
  let component: CalanderRangeComponent;
  let fixture: ComponentFixture<CalanderRangeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CalanderRangeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CalanderRangeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
