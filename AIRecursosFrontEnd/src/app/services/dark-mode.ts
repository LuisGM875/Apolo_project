// dark-mode.service.ts
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DarkModeService {
  private darkModeSubject: BehaviorSubject<boolean>;

  constructor() {
    const savedMode = localStorage.getItem('darkMode') === 'true';
    this.darkModeSubject = new BehaviorSubject<boolean>(savedMode);
  }

  isDarkMode() {
    return this.darkModeSubject.asObservable();
  }

  toggleDarkMode() {
    const currentMode = !this.darkModeSubject.value;
    this.darkModeSubject.next(currentMode);
    localStorage.setItem('darkMode', currentMode.toString());
  }
}
