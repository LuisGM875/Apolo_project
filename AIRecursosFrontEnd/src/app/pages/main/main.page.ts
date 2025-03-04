import { Component, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';
import { AuthGoogleServiceService } from 'src/app/services/auth-google-service.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.page.html',
  styleUrls: ['./main.page.scss'],
})
export class MainPage implements OnInit {

  constructor(private navCtrl: NavController, private authGoogleService: AuthGoogleServiceService) { }

  ngOnInit() {
  }

  showData() {
    const data = JSON.stringify(this.authGoogleService.getProfile())
    console.log(data);
  }

  logOut(){
    this.authGoogleService.logout(); //cierra la sesión 
    this.navCtrl.navigateForward('/login'); // redirige a la pagina de login 
  }

}
