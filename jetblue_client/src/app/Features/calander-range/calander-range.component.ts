import {
  Component,
  EventEmitter,
  Input,
  Output,
  ViewChild,
} from '@angular/core';
import { CalendarModule } from 'primeng/calendar';
import { FormsModule } from '@angular/forms';
import { CalendarInfo, CalendarType } from '../../../utils/types';

@Component({
  selector: 'app-calander-range',
  standalone: true,
  imports: [CalendarModule, FormsModule],
  templateUrl: './calander-range.component.html',
  styleUrl: './calander-range.component.scss',
})
export class CalanderRangeComponent {
  @ViewChild('myCalendar') datePicker: any;
  @Input() CalendarState: string = 'false';
  @Input() CalendarType: CalendarType = 'range';
  // Output from Calendar selection
  @Output() calenderInfo = new EventEmitter<CalendarInfo>();

  date?: Date[];
  minDate: Date | undefined;
  maxDate: Date | undefined;

  // Departure Time
  departureDate: Date | undefined;
  // Arrival time
  arrivalDate: Date | undefined;

  constructor() {
    let today = new Date();
    today.setDate(new Date().getDate() + 1);
    let tomorrow = new Date(today);
    tomorrow.setDate(today.getDate() + 356);

    this.minDate = today;
    this.maxDate = tomorrow;
  }

  openData($event: Date) {
    let departure: Date | string;
    let arrival: Date | string;
    if (this.CalendarType == 'single') {
      departure = $event;
      this.calenderInfo.emit({
        departure: departure,
        arrival: '',
      });
    } else {
      departure = this.date == undefined ? '' : this.date[0];
      arrival = this.date == undefined ? '' : this.date[1];
      this.calenderInfo.emit({
        departure: departure,
        arrival: arrival,
      });
    }
  }

  toggleCalendar($event: any) {
    let calendar = this.datePicker.el.nativeElement as HTMLElement;
    calendar.classList.toggle('hidden');
  }
}
