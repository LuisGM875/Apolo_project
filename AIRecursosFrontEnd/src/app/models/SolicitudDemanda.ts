import { Empresa } from "./empresa";
import { Recurso } from "./recurso";

export class SolicitudDemanda {
    idSolicitudDemanda: number;
    empresa: Empresa; 
    recurso: Recurso; 
    fecha: string; 
    tarifaPropuesta: number;
    modalidad: string;
    fechaInicio: string; 
    fechaTermino: string; 
    horaInicio: string;
    horaFin: string;
    estatus: number;
  }