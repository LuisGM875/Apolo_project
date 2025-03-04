import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Evaluacion } from '../models/evaluacion';
import { environment } from 'src/environments/environment';
import { Recurso } from '../models/recurso';

@Injectable({
  providedIn: 'root'
})
export class EvaluacionService {

  constructor(
    private httpClient : HttpClient
  ) { }

  createEvaluacionService(idRecurso : number, evaluacion : Evaluacion) {
    return this.httpClient.post(`${environment.evaluacionUrl}/evaluacion/${idRecurso}`, evaluacion);
  }

  updateEvaluacionService(evaluacion : Evaluacion) {
    return this.httpClient.put(`${environment.evaluacionUrl}/evaluacion`, evaluacion);
  }

  getEvaluacionService(idRecurso : number) {
    return this.httpClient.get(`${environment.evaluacionUrl}/evaluacion/${idRecurso}`);
  }
}
