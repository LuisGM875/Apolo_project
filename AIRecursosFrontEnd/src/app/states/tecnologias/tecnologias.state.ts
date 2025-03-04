import { Injectable } from '@angular/core';
import { State, Action, StateContext, Selector } from '@ngxs/store';
import { GetTecnologias } from './tecnologias.actions';
import { Tecnologia } from 'src/app/models/tecnologia';
import { TecnologiaService } from 'src/app/services/tecnologia.service';
import { tap } from 'rxjs';
import { Recurso } from 'src/app/models/recurso';

export class TecnologiasStateModel {
  tecnologias : Tecnologia[];
  success: boolean;
}

const defaults = {
  tecnologias: [],
  success: false
};

@State<TecnologiasStateModel>({
  name: 'tecnologias',
  defaults
})
@Injectable()
export class TecnologiasState {

  @Selector()
  static tecnologias(state:TecnologiasStateModel) {
    return state.tecnologias;
  }

  @Selector()
  static success(state:TecnologiasStateModel) {
    return state.success;
  }

  constructor(
    private tecnologiaService: TecnologiaService,
  ) {}

  @Action(GetTecnologias)
  getTecnologias({ patchState }: StateContext<TecnologiasStateModel>, { }: GetTecnologias) {
    return this.tecnologiaService.getTecnologiasService().pipe(tap((tecnologias:Tecnologia[]) => {
      patchState({
        tecnologias,
        success: true
      });
    }));
  }
}
