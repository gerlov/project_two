import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImageLogoDollarComponent } from './image-logo-dollar.component';

describe('ImageLogoDollarComponent', () => {
  let component: ImageLogoDollarComponent;
  let fixture: ComponentFixture<ImageLogoDollarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ImageLogoDollarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ImageLogoDollarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
