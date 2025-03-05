import { Component, OnInit } from '@angular/core';
import { Recurso } from 'src/app/models/recurso';
import { ArchivoService } from 'src/app/services/archivo.service';
import { RecursoService } from 'src/app/services/recurso.service';
import { saveAs } from 'file-saver';
import { Archivo } from 'src/app/models/archivo';
import { Select, Store } from '@ngxs/store';
import { GetRecursos } from 'src/app/states/recursos/recursos.actions';
import { RecursosState } from 'src/app/states/recursos/recursos.state';
import { BehaviorSubject, Observable } from 'rxjs';
import { ModalController, NavParams } from '@ionic/angular';
import { DetallesRecursoPage } from '../../pages/detalles-recurso/detalles-recurso.page';
import { GetArchivo } from 'src/app/states/archivos/archivos.actions';
import { ArchivosState } from 'src/app/states/archivos/archivos.state';
import { AlertService } from 'src/app/services/alert.service';
import {DarkModeService} from "../../../../../services/dark-mode";

@Component({
  selector: 'app-list-ofertas',
  templateUrl: './list-ofertas.component.html',
  styleUrls: ['./list-ofertas.component.scss'],
})
export class ListOfertasComponent  implements OnInit {

  @Select(RecursosState.recursos)
  recursos$: Observable<Recurso[]>

  recursos: Recurso[];
  isDarkMode: boolean;
  constructor(
    private store : Store,
    private modalController : ModalController,
    private alertService : AlertService, private darkModeService: DarkModeService
  ) { }
  ngOnInit() {
    this.getRecursos();
    this.darkModeService.isDarkMode().subscribe((isDark) => {
      this.isDarkMode = isDark;
    });
  }

  toggleDarkMode() {
    this.darkModeService.toggleDarkMode();
  }

  async presentModal(recurso:Recurso) {
    const recursoOferta = new BehaviorSubject(recurso);
    const modal = await this.modalController.create({
      component: DetallesRecursoPage,
      initialBreakpoint: 0.85,
      cssClass: 'custom-modal',
      componentProps: {
        recursoOferta
      }
    });
    await modal.present();
  }

  getRecursos() {
    this.store.dispatch(new GetRecursos());
    this.recursos$.subscribe({
      next: () => {
        const recursos = this.store.selectSnapshot(RecursosState.recursos);
        this.recursos = recursos;
      }
    });
  }

  downloadArchivo(recurso: Recurso) { // Descargar el archivo
    this.store.dispatch(new GetArchivo({ idRecurso: recurso.idRecurso })).subscribe({
      next: () => {
        const success = this.store.selectSnapshot(ArchivosState.success);
        if (success) {
          const blob = this.store.selectSnapshot(ArchivosState.archivoD);
          saveAs(blob, '' + recurso.archivo.nombre);
        }
      }, error: async () => {
        await this.alertService.alertError(
          'Error al descargar el archivo'
        );
      }
    });
  }

}
