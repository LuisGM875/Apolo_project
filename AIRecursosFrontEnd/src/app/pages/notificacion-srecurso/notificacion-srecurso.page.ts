import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-notificacion-srecurso',
  templateUrl: './notificacion-srecurso.page.html',
  styleUrls: ['./notificacion-srecurso.page.scss'],
})
export class NotificacionSRecursoPage implements OnInit {

    
  notificaciones = [
    {
      titulo: 'Solicitud Aceptada',
      descripcion: 'PromoLife ha aceptado la solicitud por el recurso Arturo Perez Castro',
      logo: 'assets/imgs/promolife-logo.png',
      fecha: new Date('2024-09-29')
    },
    {
      titulo: 'Solicitud Aceptada',
      descripcion: 'PromoLife ha aceptado la solicitud por el recurso Juan Muñoz Castro',
      logo: 'assets/imgs/promolife-logo.png',
      fecha: new Date('2024-10-01')
    },
    {
      titulo: 'Solicitud de Recurso',
      descripcion: 'Soultech ha solicitado a Arturo Perez Castro',
      logo: 'assets/imgs/soultech-logo.png',
      fecha: new Date('2024-09-23')
    },
    {
      titulo: 'Solicitud Renovación',
      descripcion: 'PromoLife ha aceptado la renovación por el recurso Arturo Perez Castro',
      logo: 'assets/imgs/promolife-logo.png',
      fecha: new Date('2024-09-15')
    },
    {
      titulo: 'Solicitud de Recurso',
      descripcion: 'Soultech ha solicitado a Arturo Perez Castro',
      logo: 'assets/imgs/soultech-logo.png',
      fecha: new Date('2024-08-24')
    },
    {
      titulo: 'Solicitud de Recurso',
      descripcion: 'Kohmi ha solicitado a Arturo Perez Castro',
      logo: 'assets/imgs/kohmi-logo.png',
      fecha: new Date('2024-08-15')
    }
  ];

  hoy = new Date();
  diasSiete = 7 * 24 * 60 * 60 * 1000;

  esAntigua(fechaNotificacion: Date): boolean {
    return this.hoy.getTime() - fechaNotificacion.getTime() > this.diasSiete;
  }

  esReciente(fechaNotificacion: Date): boolean {
    return this.hoy.getTime() - fechaNotificacion.getTime() <= this.diasSiete;
  }
  
  constructor() { }

  ngOnInit() {
  }

}
