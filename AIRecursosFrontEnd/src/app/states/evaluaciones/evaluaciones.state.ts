import { Injectable } from '@angular/core';
import { State, Action, StateContext, Selector } from '@ngxs/store';
import { CreateEvaluacion, UpdateEvaluacion, GetEvaluacion } from './evaluaciones.actions';
import { Evaluacion } from 'src/app/models/evaluacion';
import { EvaluacionService } from 'src/app/services/evaluacion.service';
import { tap } from 'rxjs';

export class EvaluacionesStateModel {
  evaluacion : Evaluacion;
  success : boolean;
}

const defaults = {
  evaluacion : null,
  success : false
};

@State<EvaluacionesStateModel>({
  name: 'evaluaciones',
  defaults
})
@Injectable()
export class EvaluacionesState {

  @Selector()
  static evaluacion(state:EvaluacionesStateModel) {
    return state.evaluacion;
  }

  @Selector()
  static success(state:EvaluacionesStateModel) {
    return state.success;
  }

  constructor(
    private evaluacionService : EvaluacionService
  ) {}

  @Action(CreateEvaluacion)
  createEvaluacion({ patchState }: StateContext<EvaluacionesStateModel>, { payload }: CreateEvaluacion) {
    return this.evaluacionService.createEvaluacionService(payload.idRecurso, payload.evaluacion).pipe(tap((evaluacion : Evaluacion) => {
      patchState({
        evaluacion,
        success:true
      });
    }));
  }

  @Action(UpdateEvaluacion)
  updateEvaluacion({ patchState }: StateContext<EvaluacionesStateModel>, { payload }: UpdateEvaluacion) {
    return this.evaluacionService.updateEvaluacionService(payload.evaluacion).pipe(tap((evaluacion : Evaluacion) => {
      patchState({
        evaluacion,
        success:true
      });
    }));
  }

  @Action(GetEvaluacion)
  getEvaluacion({ patchState }: StateContext<EvaluacionesStateModel>, { payload }: GetEvaluacion) {
    return this.evaluacionService.getEvaluacionService(payload.idRecurso).pipe(tap((evaluacion : Evaluacion) => {
      console.log(evaluacion);  
      patchState({
        evaluacion,
        success:true
      });
    }));
  }

}
