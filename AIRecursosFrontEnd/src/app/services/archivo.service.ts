import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ArchivoService {
  constructor(
    private httpClient : HttpClient
  ) { }

  createArchivoService(file:FormData) {
    return this.httpClient.post<any>(`${environment.archivoUrl}/archivo`, file);
  }

  updateArchivoService(idArchivo:number, file:FormData) {
    return this.httpClient.put(`${environment.archivoUrl}/archivo/${idArchivo}`, file);
  }

  getArchivoByRecursoService(idRecurso:number) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/pdf'
    });
    return this.httpClient.get(`${environment.archivoUrl}/archivo/${idRecurso}`, {responseType: 'blob', headers});
  }

  deleteArchivoService(idArchivo:number) {
    return this.httpClient.delete(`${environment.archivoUrl}/archivo/${idArchivo}`);
  }

}
