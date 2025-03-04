import { tiposNotificaciones } from "./tipoNotificaciones";

export class Notificacion {
    idNotificacion: number;
    tipoNotificacion: tiposNotificaciones;
    texto: string;
    estatus: number;
  }