import { Component } from '@angular/core';
import { MatIcon } from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';

@Component({
  selector: 'app-footer',
  standalone: true,
  imports: [MatIcon,MatDividerModule],
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.scss',
})
export class FooterComponent {}
