import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoopusComponent } from './poopus.component';

describe('PoopusComponent', () => {
  let component: PoopusComponent;
  let fixture: ComponentFixture<PoopusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PoopusComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PoopusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
