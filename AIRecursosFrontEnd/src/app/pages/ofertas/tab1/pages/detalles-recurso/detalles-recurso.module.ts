import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { DetallesRecursoPageRoutingModule } from './detalles-recurso-routing.module';

import { DetallesRecursoPage } from './detalles-recurso.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    DetallesRecursoPageRoutingModule
  ],
  declarations: [DetallesRecursoPage]
})
export class DetallesRecursoPageModule {}
