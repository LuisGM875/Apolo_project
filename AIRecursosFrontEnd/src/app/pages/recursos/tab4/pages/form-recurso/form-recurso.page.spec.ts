import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormRecursoPage } from './form-recurso.page';

describe('FormRecursoPage', () => {
  let component: FormRecursoPage;
  let fixture: ComponentFixture<FormRecursoPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(FormRecursoPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
