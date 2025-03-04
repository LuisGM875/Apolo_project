import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { NotificacionSRecursoPageRoutingModule } from './notificacion-srecurso-routing.module';

import { NotificacionSRecursoPage } from './notificacion-srecurso.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    NotificacionSRecursoPageRoutingModule
  ],
  declarations: [NotificacionSRecursoPage]
})
export class NotificacionSRecursoPageModule {}
