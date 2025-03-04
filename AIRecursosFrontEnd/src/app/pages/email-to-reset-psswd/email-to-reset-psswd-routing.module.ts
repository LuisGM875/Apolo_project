import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EmailToResetPsswdPage } from './email-to-reset-psswd.page';

const routes: Routes = [
  {
    path: '',
    component: EmailToResetPsswdPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EmailToResetPsswdPageRoutingModule {}
