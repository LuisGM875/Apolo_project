import { Injectable } from '@angular/core';
import { AlertController } from '@ionic/angular';

@Injectable({
  providedIn: 'root'
})
export class AlertService {

  constructor(
    private alertController: AlertController,
  ) { }

  async alertConfirm(text: string, functionOk) {
    let message = `
      <section>
        <img src="./assets/icons/alert_icon.png" alt="alert">
      </section>
      ${text}
    `;
    const alert = await this.alertController.create({
      message,
      cssClass: 'custom-alert',
      buttons: [
        {
          text: 'Cancelar',
          role: 'cancel',
          handler: () => {

          }
        }, {
          text: 'Confirmar',
          handler: () => {
            functionOk();
          }
        }
      ]
    });

    await alert.present();
  }

  async alertSuccess(text: string) {

    let message = `
      <section>
        <img src="./assets/icons/success_icon.png" alt="Success">
      </section>
      ${text}`

    const alertPresent = await this.alertController.create({
      message, 
      cssClass: 'custom-alert',
      buttons: ['OK']
    });
    await alertPresent.present();
  }

  async alertWarning(text:string) {
    let message = `<section>
    <img src="./assets/icons/alert_icon.png" alt="alert">
  </section>
  ${text}`;
    const alertPresent = await this.alertController.create({
      message,
      cssClass: 'custom-alert',
      buttons: ['OK']
    });
    await alertPresent.present();
  }

  async alertError(text:string) {
    let message = `<section>
    <img src="./assets/icons/error_icon.png" alt="alert">
  </section>
  ${text}`;
    const alertPresent = await this.alertController.create({
      message,
      cssClass: 'custom-alert',
      buttons: ['OK']
    });
    await alertPresent.present();

  }

}

