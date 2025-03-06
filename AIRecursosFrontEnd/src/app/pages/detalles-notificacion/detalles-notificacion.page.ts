import {Component, HostListener, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NavController, ToastController, AlertController } from '@ionic/angular';
import {DarkModeService} from "../../services/dark-mode";
import {SoundService} from "../../sound.service";


@Component({
  selector: 'app-detalles-notificacion',
  templateUrl: './detalles-notificacion.page.html',
  styleUrls: ['./detalles-notificacion.page.scss'],
})
export class DetallesNotificacionPage implements OnInit {
  notificacionId: number;
  notificacion: any;

  isDarkMode: boolean;
  isSoundEnabled: boolean;

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


  constructor(private route: ActivatedRoute, private router: Router, private navCtrl: NavController, private darkModeService: DarkModeService, private toastController: ToastController,
              private alertController: AlertController, private soundService: SoundService,) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.notificacionId = +params['id'];
      this.notificacion = this.notificaciones.find(n => n.id === this.notificacionId);
    });

    this.darkModeService.isDarkMode().subscribe((isDark) => {
      this.isDarkMode = isDark;
    });

    this.soundService.isSoundEnabled().subscribe((isEnabled) => {
      this.isSoundEnabled = isEnabled;
    });
  }

  toggleSound() {
    this.soundService.toggleSound();
  }

  @HostListener('document:keydown', ['$event'])
  handleKeyboardEvent(event: KeyboardEvent) {
    if (this.router.url.includes('/detalles-notificacion')) {
      if (event.key === 'Enter') {
        this.accept();
      } else if (event.key === 'Escape') {
        this.deny();
      } else if (event.key === 'ArrowLeft') {
        this.back();
      }
    }
  }

  back(){
    this.navCtrl.navigateForward('/notificaciones');
  }

  toggleDarkMode() {
    this.darkModeService.toggleDarkMode();
  }


  async presentToast(message: string, color: string) {
    const toast = await this.toastController.create({
      message: message,
      duration: 2000,
      position: 'top',
      cssClass: color
    });
    toast.present();
  }

  playSound(url: string) {
    if (this.isSoundEnabled) {
      const audio = new Audio(url);
      audio.play();
    }
  }

  async presentAlert(message: string, icon: string, color: string) {
    const alert = await this.alertController.create({
      header: 'Confirmación',
      message: `<ion-icon name="${icon}" style="font-size: 2em; color: ${color};"></ion-icon> ${message}`,
      buttons: [{
        text: 'OK',
        handler: () => {
          this.back();
        }
      }]
    });
    await alert.present();
  }

  speak(message: string) {
    if (this.isSoundEnabled) {
      const speech = new SpeechSynthesisUtterance(message);
      speech.lang = 'es-ES';
      window.speechSynthesis.speak(speech);
    }
  }

  async deny() {
    this.playSound('assets/sounds/error.mp3');
    await this.presentAlert('La solicitud se queda.', 'close-circle', 'red');
    this.presentToast('Solicitud denegada se quedara en bandeja la notificación.', 'toast-red');
    this.speak('Solicitud denegada se quedara en bandeja la notificación.');
  }

  async accept() {
    this.playSound('assets/sounds/success.mp3');
    await this.presentAlert('La solicitud será procesada.', 'checkmark-circle', 'green');
    this.presentToast('Solicitud aceptada espere de 1 a 2 días para que el administrador acepte la solicitud.', 'toast-green');
    this.speak('Solicitud aceptada espere de 1 a 2 días para que el administrador acepte la solicitud.');
  }
}
