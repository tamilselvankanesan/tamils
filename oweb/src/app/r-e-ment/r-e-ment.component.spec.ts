import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { REMentComponent } from './r-e-ment.component';

describe('RuleEnrichmentComponent', () => {
  let component: REMentComponent;
  let fixture: ComponentFixture<REMentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ REMentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(REMentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
