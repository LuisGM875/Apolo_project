import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DetallesNotificacionPage } from './detalles-notificacion.page';

const routes: Routes = [
  {
    path: '',
    component: DetallesNotificacionPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DetallesNotificacionPageRoutingModule {}
