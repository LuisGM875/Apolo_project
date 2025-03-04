import { Injectable } from '@angular/core';
import { Recurso } from '../models/recurso';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RecursoService {

  constructor(
    private httpClient : HttpClient
  ) { }

  createRecursoService(recurso:Recurso) {
    return this.httpClient.post<Recurso>(`${environment.recursoUrl}/recurso`, recurso);
  }

  updateRecursoService(idRecurso:number, recurso:Recurso) {
    return this.httpClient.put(`${environment.recursoUrl}/recurso/${idRecurso}`, recurso);
  }

  getRecursosService() {
    return this.httpClient.get<Recurso[]>(`${environment.recursoUrl}/recursos`);
  }

  getRecursoService(idRecurso:number) {
    return this.httpClient.get(`${environment.recursoUrl}/recurso/${idRecurso}`);
  }

  deleteRecursoService(idRecurso:number) {
    return this.httpClient.delete(`${environment.recursoUrl}/recurso/${idRecurso}`)
  }

}
