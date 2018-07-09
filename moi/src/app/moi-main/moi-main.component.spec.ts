import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MoiMainComponent } from './moi-main.component';

describe('MoiMainComponent', () => {
  let component: MoiMainComponent;
  let fixture: ComponentFixture<MoiMainComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MoiMainComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MoiMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
