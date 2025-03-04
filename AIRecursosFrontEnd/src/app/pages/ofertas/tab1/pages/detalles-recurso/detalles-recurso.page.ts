import { Component, Input, OnInit } from '@angular/core';
import { NavParams } from '@ionic/angular';
import { Select, Store } from '@ngxs/store';
import { saveAs } from 'file-saver';
import { BehaviorSubject, Observable } from 'rxjs';
import { Evaluacion } from 'src/app/models/evaluacion';
import { Recurso } from 'src/app/models/recurso';
import { AlertService } from 'src/app/services/alert.service';
import { ArchivoService } from 'src/app/services/archivo.service';
import { GetArchivo } from 'src/app/states/archivos/archivos.actions';
import { ArchivosState } from 'src/app/states/archivos/archivos.state';
import { GetEvaluacion } from 'src/app/states/evaluaciones/evaluaciones.actions';
import { EvaluacionesState } from 'src/app/states/evaluaciones/evaluaciones.state';

@Component({
  selector: 'app-detalles-recurso',
  templateUrl: './detalles-recurso.page.html',
  styleUrls: ['./detalles-recurso.page.scss'],
})
export class DetallesRecursoPage implements OnInit {

  @Input() recursoOferta: BehaviorSubject<Recurso>;
  recurso: Recurso;

  evaluacion : Evaluacion;
  load:boolean;

  constructor(
    private store: Store,
    private alertService: AlertService,
  ) { 
    this.load = false;
  }

  ngOnInit() {
    const recursoSelected = this.recursoOferta.value;
    this.recurso = recursoSelected;
    this.getEvaluacion(this.recurso);
  }

  getEvaluacion(recurso:Recurso) {
    this.store.dispatch(new GetEvaluacion({idRecurso:recurso.idRecurso})).subscribe({
      next: () => {
        const success = this.store.selectSnapshot(EvaluacionesState.success);
        if (success) {
          this.evaluacion = this.store.selectSnapshot(EvaluacionesState.evaluacion); 
          this.load = true;
        }
      }, error: async () => {
        await this.alertService.alertError('No se ha encontrado evaluacion');
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

  setColor(num:number) {
    return num > this.evaluacion.promedio;
  }

  getColor(num: number) {
    if (this.setColor(num)) {
      return ''
    }
    switch (num) {
      case 1:
        return 'warning';
      case 2:
        return 'warning';
      case 3:
        return 'warning';
      case 4:
        return 'warning';
      case 5:
        return 'warning';
    }

    return null;

  }

}
