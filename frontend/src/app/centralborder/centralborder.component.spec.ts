import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CentralborderComponent } from './centralborder.component';

describe('CentralborderComponent', () => {
  let component: CentralborderComponent;
  let fixture: ComponentFixture<CentralborderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CentralborderComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CentralborderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
