import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DetallesRecursoPage } from './detalles-recurso.page';

describe('DetallesRecursoPage', () => {
  let component: DetallesRecursoPage;
  let fixture: ComponentFixture<DetallesRecursoPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(DetallesRecursoPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
