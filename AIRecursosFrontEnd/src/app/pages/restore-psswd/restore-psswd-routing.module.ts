import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RestorePsswdPage } from './restore-psswd.page';

const routes: Routes = [
  {
    path: '',
    component: RestorePsswdPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class RestorePsswdPageRoutingModule {}
