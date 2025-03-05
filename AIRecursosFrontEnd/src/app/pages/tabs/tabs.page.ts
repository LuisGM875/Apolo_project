import { Component, OnInit } from '@angular/core';
import { DarkModeService } from 'src/app/services/dark-mode';

@Component({
  selector: 'app-tabs',
  templateUrl: 'tabs.page.html',
  styleUrls: ['tabs.page.scss']
})
export class TabsPage implements OnInit {

  isDarkMode: boolean;

  constructor(private darkModeService: DarkModeService) {}
  ngOnInit() {
    this.darkModeService.isDarkMode().subscribe((isDark) => {
      this.isDarkMode = isDark;
    });
  }

  toggleDarkMode() {
    this.darkModeService.toggleDarkMode();
  }
}

