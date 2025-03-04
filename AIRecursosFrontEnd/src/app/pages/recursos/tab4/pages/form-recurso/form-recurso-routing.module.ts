import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { FormRecursoPage } from './form-recurso.page';

const routes: Routes = [
  {
    path: '',
    component: FormRecursoPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class FormRecursoPageRoutingModule {}
