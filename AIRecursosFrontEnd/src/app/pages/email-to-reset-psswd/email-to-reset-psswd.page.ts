import { Component, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';

@Component({
  selector: 'app-email-to-reset-psswd',
  templateUrl: './email-to-reset-psswd.page.html',
  styleUrls: ['./email-to-reset-psswd.page.scss'],
})
export class EmailToResetPsswdPage implements OnInit {

  constructor(private navCtrl: NavController) { }

  email: string = '';

  ngOnInit() {
  }

  goTologin() {
    this.navCtrl.navigateForward('/restore-psswd');
  }

  onCancel() {

    this.navCtrl.navigateForward('/login');
  }
}
