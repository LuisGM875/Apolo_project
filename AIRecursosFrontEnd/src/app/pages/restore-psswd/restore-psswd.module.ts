import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { RestorePsswdPageRoutingModule } from './restore-psswd-routing.module';

import { RestorePsswdPage } from './restore-psswd.page';
import { TranslateModule } from '@ngx-translate/core';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RestorePsswdPageRoutingModule,
    TranslateModule
  ],
  declarations: [RestorePsswdPage]
})
export class RestorePsswdPageModule {}
