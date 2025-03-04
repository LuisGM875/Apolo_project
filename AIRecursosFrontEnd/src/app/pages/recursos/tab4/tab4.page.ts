import { Component, OnInit, ViewChild } from '@angular/core';
import { ListRecursosComponent } from './components/list-recursos/list-recursos.component';

@Component({
  selector: 'app-tab4',
  templateUrl: './tab4.page.html',
  styleUrls: ['./tab4.page.scss'],
})
export class Tab4Page {

  @ViewChild(ListRecursosComponent, {static: false}) manage: ListRecursosComponent;

  constructor() { 
    
  }

  // ionViewWillEnter() {
  //   if (this.manage) {
  //     this.manage.getRecursos();
  //     this.navParams.data['recurso'] = null;
  //   }
  // }

}
