import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RestorePsswdPage } from './restore-psswd.page';

describe('RestorePsswdPage', () => {
  let component: RestorePsswdPage;
  let fixture: ComponentFixture<RestorePsswdPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(RestorePsswdPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
