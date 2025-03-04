import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DetallesNotificacionPage } from './detalles-notificacion.page';

describe('DetallesNotificacionPage', () => {
  let component: DetallesNotificacionPage;
  let fixture: ComponentFixture<DetallesNotificacionPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(DetallesNotificacionPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
