import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SoundService {
  private soundEnabled = new BehaviorSubject<boolean>(true);

  isSoundEnabled() {
    return this.soundEnabled.asObservable();
  }

  toggleSound() {
    this.soundEnabled.next(!this.soundEnabled.value);
  }
}
