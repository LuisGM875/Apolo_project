import { Component, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';
import { DarkModeService } from 'src/app/services/dark-mode';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent  implements OnInit {
  isDarkMode: boolean;
  constructor( private navCtrl: NavController, private darkModeService: DarkModeService ) { }


  noti(){
    this.navCtrl.navigateForward('/notificaciones');
  }

  ngOnInit() {
    this.darkModeService.isDarkMode().subscribe((isDark) => {
      this.isDarkMode = isDark;
    });
  }

  toggleDarkMode() {
    this.darkModeService.toggleDarkMode();
  }

}
