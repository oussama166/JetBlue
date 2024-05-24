import {Component, Input} from '@angular/core';
import {NgClass, NgIf} from "@angular/common";

@Component({
  selector: 'app-card',
  standalone: true,
  imports: [
    NgClass,
    NgIf
  ],
  templateUrl: './card.component.html',
  styleUrl: './card.component.scss',
})
export class CardComponent {
  // Input from the parent element
  @Input() TypePass: "Basic" | "Blue" | "Blue Extra" | "Mint" = "Basic";
  @Input() State: "enable" | "disable"  = "enable";
  @Input() Price!: string;
  @Input() Adventage: string[] = [];
}
