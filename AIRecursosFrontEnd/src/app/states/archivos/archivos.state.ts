import { effect, Injectable } from '@angular/core';
import { State, Action, StateContext, Selector } from '@ngxs/store';
import { CreateArchivo, GetArchivo, UpdateArchivo } from './archivos.actions';
import { ArchivoService } from 'src/app/services/archivo.service';
import { tap } from 'rxjs';
import { Archivo } from 'src/app/models/archivo';

export class ArchivosStateModel {
  archivo:Archivo;
  archivoD:Blob;
  success:boolean;
}

const defaults = {
  archivo:null,
  archivoD:null,
  success:false
};

@State<ArchivosStateModel>({
  name: 'archivos',
  defaults
})
@Injectable()
export class ArchivosState {

  @Selector()
  static archivo(state:ArchivosStateModel) {
    return state.archivo;
  }

  @Selector()
  static archivoD(state:ArchivosStateModel) {
    return state.archivoD;
  }

  @Selector()
  static success(state:ArchivosStateModel) {
    return state.success;
  }

  constructor(
    private archivoService:ArchivoService,
  ) {}

  @Action(CreateArchivo)
  createArchivo({ patchState }: StateContext<ArchivosStateModel>, { payload }: CreateArchivo) {
    return this.archivoService.createArchivoService(payload.formData).pipe(tap((archivo:Archivo) => {
      patchState({
        archivo,
        success:true
      })
    }));
  }

  @Action(UpdateArchivo)
  updateArchivo({ patchState }: StateContext<ArchivosStateModel>, { payload }: UpdateArchivo) {
    return this.archivoService.updateArchivoService(payload.idArchivo, payload.formData).pipe(tap((archivo:Archivo) => {
      patchState({
        archivo,
        success:true
      })
    }));
  }
  
  @Action(GetArchivo)
  getArchivo({ patchState }: StateContext<ArchivosStateModel>, { payload }: GetArchivo) {
    return this.archivoService.getArchivoByRecursoService(payload.idRecurso).pipe(tap((archivoD: Blob) => {
      patchState({
        archivoD,
        success: true
      });
    }));
  }
}
