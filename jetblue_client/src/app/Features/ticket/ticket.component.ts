import {Component, ElementRef, ViewChild, viewChild} from '@angular/core';
import {MatIcon} from '@angular/material/icon';
import {ButtonModule} from 'primeng/button';
import {DialogModule} from 'primeng/dialog';
import {DividerModule} from 'primeng/divider';
import {TimelineModule} from 'primeng/timeline';
import {CardComponent} from "../card/card.component";
import {NgClass} from "@angular/common";

@Component({
  selector: 'app-ticket',
  standalone: true,
  templateUrl: './ticket.component.html',
  styleUrl: './ticket.component.scss',
  imports: [MatIcon, DividerModule, DialogModule, ButtonModule, TimelineModule, CardComponent, NgClass]
})
export class TicketComponent {
  // Private variables
  visible: boolean = false;
  events: any[];
  Pass: boolean = false;
  // add two view child
  @ViewChild('basicInfo') basicInfo!: any;
  @ViewChild('seatMap') seatMap!: any;

  constructor() {
    this.events = [
      {
        status: 'JFK',
        date: '15/10/2020 10:30',
        terminal: 'Terminal 2',
        icon: 'pi pi-circle-fill',
      },
      {
        status: 'LAX',
        date: '15/10/2020 10:30',
        terminal: 'Terminal 5',
        icon: 'pi pi-circle-fill',
      },
    ];
  }

  // Public methods
  showDialog() {
    this.visible = true;
  }

  activeIndex: number | null = 0;

  switchState(index: number): void {
    if (this.activeIndex === index) {
      this.activeIndex = null; // Deactivate if already active
    } else {
      this.activeIndex = index; // Activate the clicked h1
    }
  }

  isDisabled(index: number): boolean {
    return this.activeIndex !== index;
  }

  ShowPass() {
    this.Pass = !this.Pass;
  }
}
