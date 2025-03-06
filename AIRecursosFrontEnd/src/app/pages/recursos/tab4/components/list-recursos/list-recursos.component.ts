import { Component, OnInit } from '@angular/core';
import { Recurso } from 'src/app/models/recurso';
import { RecursoService } from 'src/app/services/recurso.service';
import { NavController, NavParams } from '@ionic/angular';
import { AlertService } from 'src/app/services/alert.service';
import { Select, Store } from '@ngxs/store';
import { DeleteRecurso, GetRecursos, UpdateRecurso } from 'src/app/states/recursos/recursos.actions';
import { RecursosState } from 'src/app/states/recursos/recursos.state';
import { Observable } from 'rxjs';
import {DarkModeService} from "../../../../../services/dark-mode";

@Component({
  selector: 'app-list-recursos',
  templateUrl: './list-recursos.component.html',
  styleUrls: ['./list-recursos.component.scss'],
})
export class ListRecursosComponent implements OnInit {

  @Select(RecursosState.recursos)
  recursos$: Observable<Recurso[]>

  recursos: Recurso[];
  recursoSelected: Recurso;
  showForm: boolean;
  edit: boolean;
  isDarkMode: boolean;

  constructor(
    private navParams: NavParams,
    private navController: NavController,
    private alertService: AlertService,
    private store: Store,
    private darkModeService: DarkModeService
  ) {
  }

  ngOnInit() {
    this.restartV();
    this.store.dispatch(new GetRecursos());
    this.getRecursos();
    this.darkModeService.isDarkMode().subscribe((isDark) => {
      this.isDarkMode = isDark;
    });
  }

  toggleDarkMode() {
    this.darkModeService.toggleDarkMode();
  }

  async createRecurso() {
    await this.navController.navigateForward('/form-recurso');
  }

  async updateRecurso(recurso: Recurso) {
    this.navParams.data['recurso'] = recurso;
    await this.navController.navigateForward('/form-recurso');
  }

  getRecursos() {
    this.recursos$.subscribe({
      next: () => {
        const success = this.store.selectSnapshot(RecursosState.success);
        if (success) {
          this.recursos = this.store.selectSnapshot(RecursosState.recursos) as Recurso[];

        }
      },
      error: async () => {
        await this.alertService.alertError(
          'Error al obtener los recursos'
        );
      }
    })
  }

  async deleteRecurso(idRecurso: number) {
    let self = this;
    await this.alertService.alertConfirm(
      "¿Desea eliminar este recurso?",
      async function () {
        self.store.dispatch(new DeleteRecurso({ idRecurso: idRecurso })).subscribe({
          next: async () => {
            const success = self.store.selectSnapshot(RecursosState.success);
            if (success) {
              await self.alertService.alertSuccess(
                'Recurso Eliminado Correctamente'
              );
              self.store.dispatch(new GetRecursos);
            }
          }, error: async () => {
            await self.alertService.alertError(
              'Error al Eliminar el Recurso'
            );
          }
        });
      }
    );
  }

  async ocultarRecurso(recurso: Recurso) {
    let self = this;
    await this.alertService.alertConfirm(
      '¿Deseas ocultar este recurso?',
      function () {
        recurso.ocultar = true;
        self.store.dispatch(new UpdateRecurso({ idRecurso: recurso.idRecurso, recurso: recurso })).subscribe({
          next: async () => {
            const success = self.store.selectSnapshot(RecursosState.success);
            if (success) {
              await self.alertService.alertSuccess(
                'El recurso ha sido actualizado correctamente',
              );
            }
          }, error: async () => {
            await self.alertService.alertError(
              'Error al actualizar el recurso',
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
