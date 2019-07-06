import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VerifiedInfoComponent } from './verified-info.component';

describe('VerifiedInfoComponent', () => {
  let component: VerifiedInfoComponent;
  let fixture: ComponentFixture<VerifiedInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VerifiedInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VerifiedInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
