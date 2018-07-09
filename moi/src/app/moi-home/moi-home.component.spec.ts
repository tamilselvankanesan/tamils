import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MoiHomeComponent } from './moi-home.component';

describe('MoiHomeComponent', () => {
  let component: MoiHomeComponent;
  let fixture: ComponentFixture<MoiHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MoiHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MoiHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
