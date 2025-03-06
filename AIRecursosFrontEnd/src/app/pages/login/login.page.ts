import { AfterViewInit, Component, OnDestroy, OnInit, Renderer2} from '@angular/core';
import { NavController } from '@ionic/angular';
import { AuthGoogleServiceService } from 'src/app/services/auth-google-service.service';
import { DarkModeService } from 'src/app/services/dark-mode';

// Hola
@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  login = {
    email: '',
    password: ''
  }

  showPassword: boolean = true;
  isDarkMode: boolean;

  constructor(private navCtrl: NavController, private authGoogleService: AuthGoogleServiceService,private darkModeService: DarkModeService) { }

  ngOnInit() {
    this.darkModeService.isDarkMode().subscribe((isDark) => {
      this.isDarkMode = isDark;
    });
    this.checkAuth();
  }

  toggleDarkMode() {
    this.darkModeService.toggleDarkMode();
  }

  checkAuth() {
    if (this.authGoogleService.isAuthenticated()) {
      this.goToHome(); // Redirige a la página principal si ya está autenticado
    }
  }

  logAuth() {
    //aquí se incluirá la lógica para iniciar sesión con email y contraseña
    // Por ejemplo:
    // if (this.login.email && this.login.password) {
    //   try {
    //     await this.authService.loginWithEmailAndPassword(this.login.email, this.login.password);
    //     this.goToHome();
    //   } catch (error) {
    //     console.error('Error al iniciar sesión:', error);
    //     // Manejar el error, por ejemplo, mostrar un mensaje al usuario
    //   }
    // }
    this.authGoogleService.loginGoogle(); //llama al método para iniciar sesión con Google.
  }

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }

  goToHome() {
    this.navCtrl.navigateForward('/tabs'); // Asegúrate de que la ruta sea correcta
  }

  goToForget() {
    this.navCtrl.navigateForward('/email-to-reset-psswd');

  }

  // goTomain() { /////////////////////////
  //   this.navCtrl.navigateForward('/main');
  // }

  regis() {
    this.navCtrl.navigateForward('/registration');
  }

}
