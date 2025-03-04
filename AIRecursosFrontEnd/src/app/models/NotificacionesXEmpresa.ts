import { Empresa } from "./empresa";
import { Notificacion } from "./Notificaciones";
import { Recurso } from "./recurso";
import { SolicitudesOfertaXDemanda } from "./SolicitudesOfertaXDemanda";

export class NotificacionesEmpresa {
    idNotificacionXEmpresa: number;
    Notificacion: Notificacion; 
    empresa: Empresa; 
    recurso: Recurso; 
    solicitudesOfertaXDemanda: SolicitudesOfertaXDemanda;
  }