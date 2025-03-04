import { Component, OnInit } from '@angular/core';
import { NavController, NavParams } from '@ionic/angular';
import { Select, Store } from '@ngxs/store';
import { Observable } from 'rxjs';
import { Recurso } from 'src/app/models/recurso';
import { AlertService } from 'src/app/services/alert.service';
import { RecursoService } from 'src/app/services/recurso.service';
import { DeleteRecurso, GetRecursos, UpdateRecurso } from 'src/app/states/recursos/recursos.actions';
import { RecursosState } from 'src/app/states/recursos/recursos.state';

@Component({
  selector: 'app-list-ocultos',
  templateUrl: './list-ocultos.component.html',
  styleUrls: ['./list-ocultos.component.scss'],
})
export class ListOcultosComponent implements OnInit {

  @Select(RecursosState.recursos)
  recursos$: Observable<Recurso[]>

  recursos: Recurso[];
  recursoSelected: Recurso;
  showForm: boolean;
  edit: boolean;

  constructor(
    private navParams: NavParams,
    private navController: NavController,
    private alertService: AlertService,
    private store: Store,
  ) {
  }

  ngOnInit() {
    this.restartV();
    this.store.dispatch(new GetRecursos());
    this.getRecursos();
  }

  async createRecurso() {
    await this.navController.navigateForward('/form-recurso');
  }

  updateRecurso(recurso: Recurso) {
    this.navParams.data['recurso'] = recurso;
    this.navController.navigateForward('/form-recurso');
  }

  getRecursos() {
    this.recursos$.subscribe({
      next: () => {
        const recursos = this.store.selectSnapshot(RecursosState.recursos);
        this.recursos = recursos;
      }
    });
  }

  async deleteRecurso(idRecurso: number) {
    let self = this;
    await this.alertService.alertConfirm(
      "¿Desea eliminar este recurso?",
      async function () {
        self.store.dispatch(new DeleteRecurso({ idRecurso })).subscribe({
          next: async () => {
            const success = self.store.selectSnapshot(RecursosState.success);
            if (success) {
              await self.alertService.alertSuccess(
                'Recurso Eliminado Correctamente'
              );
              self.store.dispatch(new GetRecursos());
            }
          }, error: async () => {
            await self.alertService.alertError(
              'Error al eliminar el recurso'
            );
          }
        });
      }
    );
  }

  async mostrarRecurso(recurso: Recurso) {
    let self = this;
    await this.alertService.alertConfirm(
      '¿Desea mostrar este recurso?',
      function () {
        recurso.ocultar = false;
        self.store.dispatch(new UpdateRecurso({ idRecurso: recurso.idRecurso, recurso: recurso })).subscribe({
          next: async () => {
            const success = self.store.selectSnapshot(RecursosState.success);
            if (success) {
              await self.alertService.alertSuccess(
                'El recurso ha sido actualizado correctamente'
              );
            }
          },
          error: async () => {
            await self.alertService.alertWarning(
              'Error al mostrar el recurso'
            );
          }
        });
      }
    );
  }

  restartV() {
    this.showForm = false;
    this.recursos = [];
    this.recursoSelected = null;
    this.edit = false;
  }

}
