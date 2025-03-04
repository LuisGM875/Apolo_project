import { Component, ViewChild } from '@angular/core';
import { ListRecursosComponent } from '../../recursos/tab4/components/list-recursos/list-recursos.component';
import { ListOfertasComponent } from './components/list-ofertas/list-ofertas.component';


@Component({
  selector: 'app-tab1',
  templateUrl: 'tab1.page.html',
  styleUrls: ['tab1.page.scss']
})
export class Tab1Page {

  @ViewChild(ListOfertasComponent, {static: false}) manage: ListOfertasComponent;

  constructor() {}

  ionViewWillEnter() {
    if (this.manage) {
      this.manage.getRecursos();
    }
  }

}
