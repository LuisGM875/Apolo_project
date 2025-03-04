import { Component, ViewChild } from '@angular/core';
import { ListOcultosComponent } from './components/list-ocultos/list-ocultos.component';

@Component({
  selector: 'app-tab3',
  templateUrl: 'tab3.page.html',
  styleUrls: ['tab3.page.scss']
})
export class Tab3Page {

  @ViewChild(ListOcultosComponent, {static: false}) manage: ListOcultosComponent;

  constructor() {}

  ionViewWillEnter() {
    if (this.manage) {
      this.manage.getRecursos();
    }
  }

}
