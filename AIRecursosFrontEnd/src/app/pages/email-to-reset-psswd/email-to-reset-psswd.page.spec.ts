import { ComponentFixture, TestBed } from '@angular/core/testing';
import { EmailToResetPsswdPage } from './email-to-reset-psswd.page';

describe('EmailToResetPsswdPage', () => {
  let component: EmailToResetPsswdPage;
  let fixture: ComponentFixture<EmailToResetPsswdPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(EmailToResetPsswdPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
