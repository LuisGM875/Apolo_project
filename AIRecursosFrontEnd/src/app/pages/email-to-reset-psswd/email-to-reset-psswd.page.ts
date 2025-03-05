import { Component, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';
import { DarkModeService } from 'src/app/services/dark-mode';

@Component({
  selector: 'app-email-to-reset-psswd',
  templateUrl: './email-to-reset-psswd.page.html',
  styleUrls: ['./email-to-reset-psswd.page.scss'],
})
export class EmailToResetPsswdPage implements OnInit {

  constructor(private navCtrl: NavController,private darkModeService: DarkModeService) { }

  email: string = '';
  isDarkMode: boolean;

  ngOnInit() {
    this.darkModeService.isDarkMode().subscribe((isDark) => {
      this.isDarkMode = isDark;
    });
  }

  toggleDarkMode() {
    this.darkModeService.toggleDarkMode();
  }

  goTologin() {
    this.navCtrl.navigateForward('/restore-psswd');
  }

  onCancel() {

    this.navCtrl.navigateForward('/login');
  }
}
