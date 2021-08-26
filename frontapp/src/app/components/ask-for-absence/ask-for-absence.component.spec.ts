import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AskForAbsenceComponent } from './ask-for-absence.component';

describe('AskForAbsenceComponent', () => {
  let component: AskForAbsenceComponent;
  let fixture: ComponentFixture<AskForAbsenceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AskForAbsenceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AskForAbsenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
