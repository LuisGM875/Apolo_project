import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { EmailToResetPsswdPageRoutingModule } from './email-to-reset-psswd-routing.module';

import { EmailToResetPsswdPage } from './email-to-reset-psswd.page';
import { TranslateModule } from '@ngx-translate/core';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    EmailToResetPsswdPageRoutingModule,
    TranslateModule
  ],
  declarations: [EmailToResetPsswdPage]
})
export class EmailToResetPsswdPageModule {}
