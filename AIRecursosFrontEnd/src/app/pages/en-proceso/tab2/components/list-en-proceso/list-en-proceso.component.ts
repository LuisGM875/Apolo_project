import { Component, OnInit } from '@angular/core';
import {DarkModeService} from "../../../../../services/dark-mode";

@Component({
  selector: 'app-list-en-proceso',
  templateUrl: './list-en-proceso.component.html',
  styleUrls: ['./list-en-proceso.component.scss'],
})
export class ListEnProcesoComponent implements OnInit {
  showSolicitadosMessage: boolean = false;
  showDemandadosCard: boolean = false;
  isDarkMode: boolean;

  constructor(private darkModeService: DarkModeService) { }

  ngOnInit() {

    this.darkModeService.isDarkMode().subscribe((isDark) => {
      this.isDarkMode = isDark;
    });
  }

  toggleDarkMode() {
    this.darkModeService.toggleDarkMode();
  }

  onSolicitadosClick() {
    this.showSolicitadosMessage = true;
    this.showDemandadosCard = false;
  }

  onDemandadosClick() {
    this.showSolicitadosMessage = false;
    this.showDemandadosCard = true;
  }
}
