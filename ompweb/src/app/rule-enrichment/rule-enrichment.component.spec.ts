import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RuleEnrichmentComponent } from './rule-enrichment.component';

describe('RuleEnrichmentComponent', () => {
  let component: RuleEnrichmentComponent;
  let fixture: ComponentFixture<RuleEnrichmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RuleEnrichmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RuleEnrichmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
