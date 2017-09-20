import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SateComponent } from './sate.component';

describe('SateComponent', () => {
  let component: SateComponent;
  let fixture: ComponentFixture<SateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
