import { Component, OnInit } from '@angular/core';
import { DarkModeService } from 'src/app/services/dark-mode';
import { ToastController } from '@ionic/angular';

@Component({
  selector: 'app-tabs',
  templateUrl: 'tabs.page.html',
  styleUrls: ['tabs.page.scss']
})
export class TabsPage implements OnInit {

  isDarkMode: boolean;

  constructor(
    private darkModeService: DarkModeService,
    private toastController: ToastController
  ) {}


  ngOnInit() {
    this.darkModeService.isDarkMode().subscribe((isDark) => {
      this.isDarkMode = isDark;
    });
  }

  toggleDarkMode() {
    this.darkModeService.toggleDarkMode();
  }

  showToast = false;

  async onTabChange() {
    const toast = await this.toastController.create({
      message: 'Cambiando tab',
      duration: 2000,
      position: 'bottom',
      color: 'dark',
    });

    await toast.present();
  }
}



