import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { FormRecursoPageRoutingModule } from './form-recurso-routing.module';

import { FormRecursoPage } from './form-recurso.page';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    FormRecursoPageRoutingModule,
    ReactiveFormsModule,
    NgMultiSelectDropDownModule.forRoot(),
  ],
  declarations: [FormRecursoPage]
})
export class FormRecursoPageModule {}
