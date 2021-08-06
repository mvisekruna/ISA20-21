import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomePagePharmacyAdminComponent } from './home-page-pharmacy-admin.component';

describe('HomePagePharmacyAdminComponent', () => {
  let component: HomePagePharmacyAdminComponent;
  let fixture: ComponentFixture<HomePagePharmacyAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomePagePharmacyAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomePagePharmacyAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
