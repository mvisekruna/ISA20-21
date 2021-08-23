import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewPharmacistOrDermatologistComponent } from './add-new-pharmacist-or-dermatologist.component';

describe('AddNewPharmacistOrDermatologistComponent', () => {
  let component: AddNewPharmacistOrDermatologistComponent;
  let fixture: ComponentFixture<AddNewPharmacistOrDermatologistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddNewPharmacistOrDermatologistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddNewPharmacistOrDermatologistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
