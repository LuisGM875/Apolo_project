import { Evaluacion } from "src/app/models/evaluacion";

export class CreateEvaluacion {
  static readonly type = 'Create Evaluacion';
  constructor(public payload: {idRecurso:number, evaluacion : Evaluacion}) { }
}

export class UpdateEvaluacion {
  static readonly type = 'Update Evaluacion';
  constructor(public payload: {evaluacion : Evaluacion}) { }
}

export class GetEvaluacion {
  static readonly type = 'Get Evaluacion';
  constructor(public payload: {idRecurso : number}) { }
}
