import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFreeExaminationComponent } from './add-free-examination.component';

describe('AddFreeExaminationComponent', () => {
  let component: AddFreeExaminationComponent;
  let fixture: ComponentFixture<AddFreeExaminationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddFreeExaminationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddFreeExaminationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
