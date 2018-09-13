import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JrpHomeComponent } from './jrp-home.component';

describe('JrpHomeComponent', () => {
  let component: JrpHomeComponent;
  let fixture: ComponentFixture<JrpHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JrpHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JrpHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
