import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlightOneWayComponent } from './flight-one-way.component';

describe('FlightOneWayComponent', () => {
  let component: FlightOneWayComponent;
  let fixture: ComponentFixture<FlightOneWayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FlightOneWayComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FlightOneWayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
