// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  recursoUrl: 'http://localhost:8080/api/v1/r',
  tecnologiaUrl: 'http://localhost:8080/api/v1/t',
  empresaUrl: 'http://localhost:8080/api/v1/e',
  archivoUrl: 'http://localhost:8080/api/v1/a/recurso',
  evaluacionUrl: 'http://localhost:8080/api/v1/e/recurso',
  experienciaUrl: 'http://localhost:8080/api/v1/e',
  modalidadUrl: 'http://localhost:8080/api/v1/m',
  puestoUrl: 'http://localhost:8080/api/v1/p',
  tiposNotificacionUrl: 'http://localhost:8080/api/v1/tipos-notificaciones',
  notificacionesUrl: 'http://localhost:8080/api/v1/Catnotificaciones',
  solicitudesDemandaUrl: 'http://localhost:8080/api/v1/r',
  authUrl: 'http://localhost:8080/api/v1/auth',
  solicitudesOfertaXDemandaUrl: 'http://localhost:8080/api/v1/solicitudes-oferta-demanda',
  notificacionesXEmpresaUrl: 'http://localhost:8080/api/v1/notificaciones-empresa',
  solicitudesOfertaUrl: 'http://localhost:8080/api/v1/r',
  sendTokenUrl: 'http://localhost:8080/api/v1/email/send-token',
  jwtUrl: 'http://localhost:8080/api/v1/validate-token',
  passwordNewUrl: 'http://localhost:8080/api/v1/reset-password'
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
