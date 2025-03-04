import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { RegistrationPageRoutingModule } from './registration-routing.module';

import { RegistrationPage } from './registration.page';
import { ProfilePhotoOptionComponent } from '../components/profile-photo-option/profile-photo-option.component';
import {TranslateModule} from "@ngx-translate/core";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RegistrationPageRoutingModule,
    TranslateModule
  ],
  declarations: [RegistrationPage,
    ProfilePhotoOptionComponent
  ]
})
export class RegistrationPageModule {}
