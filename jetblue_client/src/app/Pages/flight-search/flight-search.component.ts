import { Component } from '@angular/core';
import { MatIcon } from '@angular/material/icon';
import { TicketComponent } from "../../Features/ticket/ticket.component";
import { DataViewModule } from 'primeng/dataview';

@Component({
  selector: 'app-flight-search',
  standalone: true,
  templateUrl: './flight-search.component.html',
  styleUrl: './flight-search.component.scss',
  imports: [MatIcon, TicketComponent, DataViewModule],
})
export class FlightSearchComponent {}
