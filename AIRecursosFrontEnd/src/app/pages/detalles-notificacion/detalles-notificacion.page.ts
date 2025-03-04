import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NavController } from '@ionic/angular';

@Component({
  selector: 'app-detalles-notificacion',
  templateUrl: './detalles-notificacion.page.html',
  styleUrls: ['./detalles-notificacion.page.scss'],
})
export class DetallesNotificacionPage implements OnInit {
  notificacionId: number;
  notificacion: any;

  notificaciones = [
    {
      id: 1,
      titulo: 'Solicitud Aceptada',
      empresa: 'PromoLife',
      descripcion: ' ha aceptado la solicitud por el recurso ',
      logo: 'assets/imgs/promolife-logo.png',
      fecha: new Date('2024-10-05'),
      tarifa: 25000,
      horario: '08:00 am a 06:00 pm',
      fechaInicio: '13/06/2024',
      fechaFin: '14/07/2024',
      modalidad: 'hibrida',
      recurso: 'Arturo Perez Castro'
    },
    {
      id: 2,
      titulo: 'Solicitud Aceptada',
      empresa: 'PromoLife',
      descripcion: ' ha aceptado la solicitud por el recurso ',
      logo: 'assets/imgs/promolife-logo.png',
      fecha: new Date('2024-10-04'),
      tarifa: 20000,
      horario: '09:00 am a 05:00 pm',
      fechaInicio: '01/06/2024',
      fechaFin: '01/07/2024',
      modalidad: 'remota',
      recurso: 'Juan Muñoz Castro'
    },
    {
      id: 3,
      titulo: 'Solicitud de Recurso',
      empresa: 'PromoLife',
      descripcion: ' ha solicitado a ',
      logo: 'assets/imgs/soultech-logo.png',
      fecha: new Date('2024-09-23'),
      tarifa: 30000,
      horario: '08:00 am a 04:00 pm',
      fechaInicio: '13/06/2024',
      fechaFin: '14/07/2024',
      modalidad: 'presencial',
      recurso: 'Arturo Perez Castro' 
    },
    {
      id: 4,
      titulo: 'Solicitud Renovación',
      empresa: 'PromoLife',
      descripcion: ' ha aceptado la renovación por el recurso ',
      logo: 'assets/imgs/promolife-logo.png',
      fecha: new Date('2024-09-15'),
      tarifa: 28000,
      horario: '08:00 am a 05:00 pm',
      fechaInicio: '15/07/2024',
      fechaFin: '15/08/2024',
      modalidad: 'hibrida',
      recurso: 'Arturo Perez Castro' 
    },
    {
      id: 5,
      titulo: 'Solicitud de Recurso',
      empresa: 'PromoLife',
      descripcion: ' ha solicitado a ',
      logo: 'assets/imgs/soultech-logo.png',
      fecha: new Date('2024-08-24'),
      tarifa: 24000,
      horario: '08:00 am a 04:00 pm',
      fechaInicio: '15/08/2024',
      fechaFin: '15/09/2024',
      modalidad: 'presencial',
      recurso: 'Arturo Perez Castro' 
    },
    {
      id: 6,
      titulo: 'Solicitud de Recurso',
      empresa: 'PromoLife',
      descripcion: ' ha solicitado a ',
      logo: 'assets/imgs/kohmi-logo.png',
      fecha: new Date('2024-08-15'),
      tarifa: 22000,
      horario: '07:00 am a 03:00 pm',
      fechaInicio: '13/06/2024',
      fechaFin: '14/07/2024',
      modalidad: 'remota',
      recurso: 'Arturo Perez Castro' 
    },
    {
      id: 7, 
      titulo: 'Solicitud de Recurso',
      empresa: 'TechCorp',
      descripcion: ' ha solicitado a  ',
      logo: 'assets/imgs/techcorp-logo.png',
      fecha: new Date('2024-10-08'),
      tarifa: 15000,
      horario: '10:00 am a 04:00 pm',
      fechaInicio: '01/11/2024',
      fechaFin: '10/11/2024',
      modalidad: 'remota',
      recurso: 'María López' 
    }
  ];
  

  constructor(private route: ActivatedRoute, private navCtrl: NavController) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.notificacionId = +params['id'];
      this.notificacion = this.notificaciones.find(n => n.id === this.notificacionId);
    });
  }

  back(){
    this.navCtrl.navigateForward('/notificaciones');
  }
}
