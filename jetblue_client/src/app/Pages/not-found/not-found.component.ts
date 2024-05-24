import { Component } from '@angular/core';
import { MatIcon } from '@angular/material/icon';

@Component({
  selector: 'app-not-found',
  standalone: true,
  imports: [MatIcon],
  templateUrl: './not-found.component.html',
  styleUrl: './not-found.component.scss',
})
export class NotFoundComponent {
  emptyField($event: FocusEvent | Event) {
    let inputValue = $event.srcElement as HTMLInputElement;
    if (inputValue.value === '') {
      inputValue.setAttribute('aria-expanded', 'false');
      inputValue.setAttribute('aria-selected', 'false');
    } else {
      inputValue.setAttribute('aria-expanded', 'true');
    }
  }
}
