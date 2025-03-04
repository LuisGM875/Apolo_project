import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { NotificacionSRecursoPage } from './notificacion-srecurso.page';

const routes: Routes = [
  {
    path: '',
    component: NotificacionSRecursoPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class NotificacionSRecursoPageRoutingModule {}
