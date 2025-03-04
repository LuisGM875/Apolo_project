import { Injectable } from '@angular/core';
import { State, Action, StateContext, Selector } from '@ngxs/store';
import { Recurso } from 'src/app/models/recurso';
import { RecursoService } from 'src/app/services/recurso.service';
import { tap } from 'rxjs';
import { CreateRecurso, DeleteRecurso, GetRecurso, GetRecursos, UpdateRecurso } from 'src/app/states/recursos/recursos.actions';

export class RecursosStateModel {
  recursos: Recurso[];
  recurso:Recurso;
  success: boolean;

}

const defaults = {
  recursos: [],
  recurso:null,
  success: false
};

@State<RecursosStateModel>({
  name: 'recursos',
  defaults
})
@Injectable()
export class RecursosState {

  @Selector()
  static recursos(state:RecursosStateModel) {
    return state.recursos;
  }

  @Selector()
  static recurso(state:RecursosStateModel) {
    return state.recurso;
  }

  @Selector()
  static success(state:RecursosStateModel) {
    return state.success;
  }

  constructor(
    private recursoService : RecursoService,
  ) { }

  @Action(CreateRecurso)
  createRecurso({ patchState }: StateContext<RecursosStateModel>, { payload }: CreateRecurso) {
    return this.recursoService.createRecursoService(payload.recurso).pipe(tap((recurso:Recurso) => {
      patchState({
        recurso,
        success: true
      });
    }));
  }

  @Action(UpdateRecurso)
  updateRecurso({ patchState }: StateContext<RecursosStateModel>, { payload }: UpdateRecurso) {
    return this.recursoService.updateRecursoService(payload.idRecurso, payload.recurso).pipe(tap((recurso:Recurso) => {
      patchState({
        success:true,
        recurso
       });
    }));
  }

  @Action(GetRecurso)
  getRecurso({ patchState }: StateContext<RecursosStateModel>, { payload }: GetRecurso) {

    return this.recursoService.getRecursoService(payload.idRecurso).pipe(tap((recurso:Recurso) => {
      patchState({
        recurso, 
        success:true
      });
    }));
  }

  @Action(GetRecursos)
  getRecursos({ patchState }: StateContext<RecursosStateModel>, { }: GetRecursos) {
    return this.recursoService.getRecursosService().pipe(tap((recursos:Recurso[]) => {
      patchState({
        recursos,
        success:true
      });
    }));
  }

  @Action(DeleteRecurso)
  deleteRecurso({ patchState }: StateContext<RecursosStateModel>, { payload }: DeleteRecurso) {
    return this.recursoService.deleteRecursoService(payload.idRecurso).pipe(tap(() => {
      patchState({
        success:true
      });
    }));
  }

}
