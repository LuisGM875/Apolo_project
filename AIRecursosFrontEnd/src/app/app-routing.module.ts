import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { DetallesNotificacionPage } from './pages/detalles-notificacion/detalles-notificacion.page';


const routes: Routes = [

  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    loadChildren: () => import('./pages/login/login.module').then(m => m.LoginPageModule)
  },
  {
    path: 'email-to-reset-psswd',
    loadChildren: () => import('./pages/email-to-reset-psswd/email-to-reset-psswd.module').then(m => m.EmailToResetPsswdPageModule)
  },
  {
    path: 'restore-psswd',
    loadChildren: () => import('./pages/restore-psswd/restore-psswd.module').then(m => m.RestorePsswdPageModule)
  },
  {
    path: 'registration',
    loadChildren: () => import('./pages/registration/registration.module').then(m => m.RegistrationPageModule)
  },
  {
    path: 'main',
    loadChildren: () => import('./pages/main/main.module').then(m => m.MainPageModule)
  },

  {
    path: '',
    loadChildren: () => import('./pages/tabs/tabs.module').then(m => m.TabsPageModule)
  },

  {
    path: 'form-recurso',
    loadChildren: () => import('./pages/recursos/tab4/pages/form-recurso/form-recurso.module').then( m => m.FormRecursoPageModule)
  },

  {
    path: 'notificaciones',
    loadChildren: () => import('./pages/notificaciones/notificaciones.module').then( m => m.NotificacionesPageModule)
  },
  
  {
    path: 'notificacion-srecurso',
    loadChildren: () => import('./pages/notificacion-srecurso/notificacion-srecurso.module').then( m => m.NotificacionSRecursoPageModule)
  },

  {
    path: 'detalles-notificacion',
    loadChildren: () => import('./pages/detalles-notificacion/detalles-notificacion.module').then( m => m.DetallesNotificacionPageModule)
  },

  {
    path: 'detalles-notificacion/:id',
    component: DetallesNotificacionPage
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
