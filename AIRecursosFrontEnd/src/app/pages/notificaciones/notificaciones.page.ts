import { Component, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';
import {DarkModeService} from "../../services/dark-mode";

@Component({
  selector: 'app-notificaciones',
  templateUrl: './notificaciones.page.html',
  styleUrls: ['./notificaciones.page.scss'],
})
export class NotificacionesPage implements OnInit {

  isDarkMode: boolean;

  notificaciones = [
    {
      id: 1,
      titulo: 'Solicitud Aceptada',
      descripcion: 'PromoLife ha aceptado la solicitud por el recurso Arturo Perez Castro',
      logo: 'assets/imgs/promolife-logo.png',
      fecha: new Date('2024-10-15'),
      tarifa: 25000,
      horario: '08:00 am a 06:00 pm',
      fechaInicio: '13/06/2024',
      fechaFin: '14/07/2024',
      modalidad: 'hibrida'
    },
    {
      id: 2,
      titulo: 'Solicitud Aceptada',
      descripcion: 'PromoLife ha aceptado la solicitud por el recurso Juan Muñoz Castro',
      logo: 'assets/imgs/promolife-logo.png',
      fecha: new Date('2024-10-04'),
      tarifa: 20000,
      horario: '09:00 am a 05:00 pm',
      fechaInicio: '01/06/2024',
      fechaFin: '01/07/2024',
      modalidad: 'remota'
    },
    {
      id: 3,
      titulo: 'Solicitud de Recurso',
      descripcion: 'Soultech ha solicitado a Arturo Perez Castro',
      logo: 'assets/imgs/soultech-logo.png',
      fecha: new Date('2024-09-23'),
      tarifa: 30000,
      horario: '08:00 am a 04:00 pm',
      fechaInicio: '13/06/2024',
      fechaFin: '14/07/2024',
      modalidad: 'presencial'
    },
    {
      id: 4,
      titulo: 'Solicitud Renovación',
      descripcion: 'PromoLife ha aceptado la renovación por el recurso Arturo Perez Castro',
      logo: 'assets/imgs/promolife-logo.png',
      fecha: new Date('2024-09-15'),
      tarifa: 28000,
      horario: '08:00 am a 05:00 pm',
      fechaInicio: '15/07/2024',
      fechaFin: '15/08/2024',
      modalidad: 'hibrida'
    },
    {
      id: 5,
      titulo: 'Solicitud de Recurso',
      descripcion: 'Soultech ha solicitado a Arturo Perez Castro',
      logo: 'assets/imgs/soultech-logo.png',
      fecha: new Date('2024-08-24'),
      tarifa: 24000,
      horario: '08:00 am a 04:00 pm',
      fechaInicio: '15/08/2024',
      fechaFin: '15/09/2024',
      modalidad: 'presencial'
    },
    {
      id: 6,
      titulo: 'Solicitud de Recurso',
      descripcion: 'Kohmi ha solicitado a Arturo Perez Castro',
      logo: 'assets/imgs/kohmi-logo.png',
      fecha: new Date('2024-08-15'),
      tarifa: 22000,
      horario: '07:00 am a 03:00 pm',
      fechaInicio: '13/06/2024',
      fechaFin: '14/07/2024',
      modalidad: 'remota'
    }
];

  hoy: Date;
  diasSiete = 7 * 24 * 60 * 60 * 1000;
  recientes: any[] = [];
  antiguas: any[] = [];
  isCalendarVisible: boolean = false;
  selectedDate: Date | undefined = undefined;

  constructor(private navCtrl: NavController, private darkModeService: DarkModeService) { }

  ngOnInit() {
    this.hoy = new Date();
    this.categorizarNotificaciones(this.notificaciones);
    this.darkModeService.isDarkMode().subscribe((isDark) => {
      this.isDarkMode = isDark;
    });
  }

  toggleDarkMode() {
    this.darkModeService.toggleDarkMode();
  }

  toggleCalendar() {
    this.isCalendarVisible = !this.isCalendarVisible;
  }

  onDateChange(event: any) {
    this.selectedDate = new Date(event.detail.value);
    this.updateNotificacionesPorFecha();
    this.isCalendarVisible = false;
  }

  updateNotificacionesPorFecha() {
    if (this.selectedDate) {
      const filteredNotificaciones = this.notificaciones.filter(notificacion => {
        const notificacionFecha = this.stripTime(new Date(notificacion.fecha));
        const selectedFecha = this.stripTime(this.selectedDate!);
        return notificacionFecha.getTime() === selectedFecha.getTime();
      });
      this.categorizarNotificaciones(filteredNotificaciones);
    }
  }

  stripTime(date: Date): Date {
    return new Date(date.getFullYear(), date.getMonth(), date.getDate());
  }

  categorizarNotificaciones(notificaciones: any[]) {
    this.recientes = notificaciones.filter(notificacion => this.esReciente(notificacion.fecha));
    this.antiguas = notificaciones.filter(notificacion => this.esAntigua(notificacion.fecha));
  }

  esReciente(fechaNotificacion: Date): boolean {
    const diferencia = this.hoy.getTime() - new Date(fechaNotificacion).getTime();
    return diferencia <= this.diasSiete;
  }

  esAntigua(fechaNotificacion: Date): boolean {
    const diferencia = this.hoy.getTime() - new Date(fechaNotificacion).getTime();
    return diferencia > this.diasSiete;
  }

  resetFilter() {
    this.selectedDate = undefined;
    this.categorizarNotificaciones(this.notificaciones);
  }

  verDetalles(notificacionId: number) {
    this.navCtrl.navigateForward(`/detalles-notificacion/${notificacionId}`);
  }

  backtomain(){
    this.navCtrl.navigateForward('/tabs');
  }
}
