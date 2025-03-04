import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { Tab4Page } from './tab4.page';

const routes: Routes = [
  {
    path: '',
    component: Tab4Page
  },
  {
    path: 'form-recurso',
    loadChildren: () => import('./pages/form-recurso/form-recurso.module').then( m => m.FormRecursoPageModule)
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class Tab4PageRoutingModule {}
