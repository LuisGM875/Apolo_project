import { Injectable } from '@angular/core';
import { AuthConfig, OAuthService } from 'angular-oauth2-oidc';

@Injectable({
  providedIn: 'root'
})
export class AuthGoogleServiceService {

  constructor(private oAuthService: OAuthService) {
    this.initLogin();
  }

  initLogin() {
    const config: AuthConfig = {
      issuer: 'https://accounts.google.com',
      strictDiscoveryDocumentValidation: false,
      clientId: '570769324589-utvhk9n3vo3pg2cdpe7ab28mn2p0olev.apps.googleusercontent.com',
      redirectUri: window.location.origin + '/main',
      scope: 'openid profile email',
    }

    this.oAuthService.configure(config);
    this.oAuthService.setupAutomaticSilentRefresh();
    // this.oAuthService.loadDiscoveryDocumentAndLogin();
  }

  loginGoogle() {
    this.oAuthService.loadDiscoveryDocument().then(() => {
      this.oAuthService.initImplicitFlow(); 
    });
  }

  logout() {
    this.oAuthService.logOut(); // esto cierra la sesión en Google 
  }

  getProfile() {
    return this.oAuthService.getIdentityClaims();
  }

  isAuthenticated():boolean {
    return this.oAuthService.hasValidAccessToken();
  }
}
