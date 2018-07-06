import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CaseSearchByCaseNumberComponent } from './case-search-by-case-number.component';

describe('CaseSearchByCaseNumberComponent', () => {
  let component: CaseSearchByCaseNumberComponent;
  let fixture: ComponentFixture<CaseSearchByCaseNumberComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CaseSearchByCaseNumberComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CaseSearchByCaseNumberComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
