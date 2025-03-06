import {ChangeDetectorRef, Component, Renderer2} from '@angular/core';
import { Device } from '@capacitor/device';
import { Platform } from '@ionic/angular';
import { TranslateService } from '@ngx-translate/core';
import {DarkModeService} from "./services/dark-mode";

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent {

  public load: boolean;
  private currentFontSize: number = 16;
  private recognition: any;
  private isRecognitionActive: boolean = false;

  constructor(
    private platform: Platform,
    private translate: TranslateService,
    private renderer: Renderer2,
    private darkModeService: DarkModeService,
    private cdr: ChangeDetectorRef
  ) {
    this.translate.setDefaultLang('es');
    this.load = false;
    this.initApp();
  }

  initApp() {
    this.platform.ready().then(async () => {
      const language = await Device.getLanguageCode();

      if (language.value) {
        this.translate.use(language.value.slice(0, 2));
      }
      this.load = true;
    });
  }

  setFontSize(size: number) {
    document.documentElement.style.setProperty('--app-font-size', `${size}px`);
  }

  startVoiceCommands() {
    if (!this.recognition) {
      this.recognition = new (window.SpeechRecognition || window.webkitSpeechRecognition)();
      this.recognition.lang = 'es-ES'; // Configura el idioma a español
      this.recognition.continuous = true;

      this.recognition.onresult = (event) => {
        const command = event.results[event.results.length - 1][0].transcript.trim().toLowerCase();
        if (command.includes('aumentar tamaño de letra')) {
          this.setFontSize(this.currentFontSize + 2);
        } else if (command.includes('disminuir tamaño de letra')) {
          this.setFontSize(this.currentFontSize - 2);
        } else if (command.includes('letra normal')) {
          this.setFontSize(16); // Tamaño de letra normal
        } else if (command.includes('letra pequeña')) {
          this.setFontSize(12); // Tamaño de letra pequeña
        } else if (command.includes('modo oscuro')) {
          this.toggleDarkMode();
          this.cdr.detectChanges();
        }
      };
    }

    if (this.isRecognitionActive) {
      this.recognition.stop();
      this.isRecognitionActive = false;
    } else {
      this.recognition.start();
      this.isRecognitionActive = true;
    }
  }

  toggleDarkMode() {
    this.darkModeService.toggleDarkMode();
  }


}
