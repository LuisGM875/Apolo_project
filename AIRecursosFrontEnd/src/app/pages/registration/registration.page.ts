import {Component, input, Renderer2, OnInit} from '@angular/core';
import { ModalController, NavController } from '@ionic/angular';
import { ProfilePhotoOptionComponent } from '../components/profile-photo-option/profile-photo-option.component';
import { Camera, CameraResultType, CameraSource } from '@capacitor/camera';
import { DarkModeService } from 'src/app/services/dark-mode';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.page.html',
  styleUrls: ['./registration.page.scss'],
})
export class RegistrationPage implements OnInit {
  photo: string | null = null;

  registration = {
    companyName: '',
    contactNumber: '',
    ReasonSocial: '',
    RFC: '',
    REPSEf: ''

  };

  newPassword: string = '';
  confirmPassword: string = '';
  showPassword: boolean = true;
  passwordMismatch: boolean = false;
  isDarkMode: boolean;

  constructor(private modalController: ModalController, private navCtrl: NavController, private darkModeService: DarkModeService) { }

  async openOptionSelection() {
    const modal = await this.modalController.create({
      component: ProfilePhotoOptionComponent,
      cssClass: 'transparente-modal'
    });

    modal.onDidDismiss().then(res => {
      if (res.role === 'select') {
        if (res.data === 'delete') {
          this.deletePhoto();
        } else {
          this.takePicture(res.data);
        }
      }
    });

    return await modal.present();

  }

  ngOnInit() {
    this.darkModeService.isDarkMode().subscribe((isDark) => {
      this.isDarkMode = isDark;
    });
  }

  toggleDarkMode() {
    this.darkModeService.toggleDarkMode();
  }

  async takePicture(type) {
    const image = await Camera.getPhoto({
      quality: 90,
      allowEditing: false,
      resultType: CameraResultType.Uri,
      source: CameraSource[type]
    });

    this.photo = image.webPath;
  }

  deletePhoto() {
    this.photo = null;
  }

  onSubmit() {
  }

  validateNumber(event: any) {
    const input = event.target as HTMLInputElement;
    const trimmed = input.value.replace(/\s+/g, '');
    const numbers = trimmed.replace(/[^0-9]/g, '');

    if (trimmed !== numbers) {
      input.value = numbers;
      this.registration.contactNumber = numbers;
    }
  }

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }

  // Verifica si las contraseñas coinciden cada vez que cambian
  // Este método se ejecuta en cada ciclo de detección de cambios de Angular
  ngDoCheck() {
    this.passwordMismatch = this.newPassword !== this.confirmPassword // Verifica si las contraseñas son diferente
      && this.newPassword !== '' // Asegura que el campo de nueva contraseña no esté vacío
      && this.confirmPassword !== ''; // Asegura que el campo de confirmación no esté vacío
  }

  regisLogin() {
    this.navCtrl.navigateForward('/login');
  }

}

