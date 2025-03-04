import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouteReuseStrategy } from '@angular/router';

import { IonicModule, IonicRouteStrategy, NavController, NavParams } from '@ionic/angular';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { NgxsModule } from '@ngxs/store';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { OAuthModule } from 'angular-oauth2-oidc';

import { RecursosState } from './states/recursos/recursos.state';
import { ArchivosState } from './states/archivos/archivos.state';
import { TecnologiasState } from './states/tecnologias/tecnologias.state';
import { EvaluacionesState } from './states/evaluaciones/evaluaciones.state';


export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json')
}

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    IonicModule.forRoot({
      innerHTMLTemplatesEnabled: true,
    }),
    NgMultiSelectDropDownModule.forRoot(),
    AppRoutingModule,
    OAuthModule.forRoot(),
    HttpClientModule,
    NgxsModule.forRoot([
      RecursosState,
      ArchivosState,
      TecnologiasState,
      EvaluacionesState,
    ]),
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    })
  ],
  providers: [{ provide: RouteReuseStrategy, useClass: IonicRouteStrategy }, NavParams],
  bootstrap: [AppComponent],
})
export class AppModule { }
