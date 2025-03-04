export class CreateArchivo {
  static readonly type = 'Create archivo';
  constructor(public payload: {formData:FormData}) { }
}

export class UpdateArchivo {
  static readonly type = 'Update archivo';
  constructor(public payload: {idArchivo:number, formData:FormData}) { }
}

export class GetArchivo {
  static readonly type = 'Get archivo';
  constructor(public payload: {idRecurso:number}) { }
}
