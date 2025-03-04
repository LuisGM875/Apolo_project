import { Empresa } from "./empresa";
import { Recurso } from "./recurso";

export class SolicitudOferta {
    idSolicitudOferta: number;
    empresa: Empresa; 
    recurso: Recurso;
    fecha: Date;
  }