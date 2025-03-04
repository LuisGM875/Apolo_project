import { Recurso } from "src/app/models/recurso";

export class CreateRecurso {
  static readonly type = 'Create Recurso';
  constructor(public payload: {recurso:Recurso}) { }
}

export class UpdateRecurso {
  static readonly type = 'Update Recurso';
  constructor(public payload: {idRecurso:number, recurso:Recurso}) { }
}

export class GetRecursos {
  static readonly type = 'Get Recursos';
}

export class GetRecurso {
  static readonly type = 'Get Recurso';
  constructor(public payload: {idRecurso:number}) { }
}

export class DeleteRecurso {
  static readonly type = 'Delete Recurso';
  constructor(public payload: {idRecurso:number}) { }
}
