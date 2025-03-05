import { Component, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';
import { DarkModeService } from 'src/app/services/dark-mode';

@Component({
  selector: 'app-restore-psswd',
  templateUrl: './restore-psswd.page.html',
  styleUrls: ['./restore-psswd.page.scss'],
})
export class RestorePsswdPage implements OnInit {

  newPassword: string = '';
  confirmPassword: string = '';
  showPassword: boolean = true;
  passwordMismatch: boolean = false;
  isDarkMode: boolean;

  constructor(private navCtrl: NavController, private darkModeService: DarkModeService) { }
  ngOnInit() {
    this.darkModeService.isDarkMode().subscribe((isDark) => {
      this.isDarkMode = isDark;
    });
  }

  toggleDarkMode() {
    this.darkModeService.toggleDarkMode();
  }

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }

  resetPassword() {
    if (this.newPassword !== this.confirmPassword) {
      this.passwordMismatch = true;
      return;
    }
    this.passwordMismatch = false;
    // Aquí iría la lógica para restablecer la contraseña
    console.log('Contraseña restablecida con éxito');
    // Navegar de vuelta al login o mostrar un mensaje de éxito
  }

  //lógica para cancelar y volver atrás
  onCancel() {
    this.navCtrl.navigateForward('/login');

  }

  // Verifica si las contraseñas coinciden cada vez que cambian
  // Este método se ejecuta en cada ciclo de detección de cambios de Angular
  ngDoCheck() {
    this.passwordMismatch = this.newPassword !== this.confirmPassword // Verifica si las contraseñas son diferente
      && this.newPassword !== '' // Asegura que el campo de nueva contraseña no esté vacío
      && this.confirmPassword !== ''; // Asegura que el campo de confirmación no esté vacío
  }
}

