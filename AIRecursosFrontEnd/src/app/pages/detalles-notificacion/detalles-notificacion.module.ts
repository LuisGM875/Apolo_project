import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { DetallesNotificacionPageRoutingModule } from './detalles-notificacion-routing.module';

import { DetallesNotificacionPage } from './detalles-notificacion.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    DetallesNotificacionPageRoutingModule
  ],
  declarations: [DetallesNotificacionPage]
})
export class DetallesNotificacionPageModule {}
