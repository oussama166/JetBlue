import { NgClass, NgForOf } from '@angular/common';
import {
  Component,
  ElementRef,
  EventEmitter,
  Input,
  Output,
  ViewChild,
  inject,
} from '@angular/core';
import { MatIconModule, MatIconRegistry } from '@angular/material/icon';
import { MatSelectModule } from '@angular/material/select';
import { DomSanitizer } from '@angular/platform-browser';
import { CityService } from './../../../../services/City/city.service';

import { City } from '../../../../models/city.model';

import { CalendarInfo, Country, InfoFlight } from '../../../../utils/types';
import { CalanderRangeComponent } from '../../calander-range/calander-range.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-flight-one-way',
  standalone: true,
  imports: [
    NgForOf,
    MatIconModule,
    MatSelectModule,
    NgClass,
    CalanderRangeComponent,
  ],
  templateUrl: './flight-one-way.component.html',
  styleUrl: './flight-one-way.component.scss',
})
export class FlightOneWayComponent {
  // Imported data from json file
  countryList: Country[] = [];
  // The result of search for the first input
  resultSerach: string[] = [];
  // The City result
  cityResult: City[] = [];

  // The arguments

  // Destination
  Origin: string = '';
  Destination: string = '';
  calendarActive: string = 'false';

  // View Child for Destination
  @ViewChild('inputOrigin') inputOrigin:
    | ElementRef<HTMLInputElement>
    | undefined;
  @ViewChild('inputDestination') inputDestination:
    | ElementRef<HTMLInputElement>
    | undefined;
  @ViewChild('departureDate') departureDate:
    | ElementRef<HTMLInputElement>
    | undefined;
  @ViewChild('arrivalDate') arrivalDate:
    | ElementRef<HTMLInputElement>
    | undefined;
  // Input and output varibale
  @Input() data!: InfoFlight;
  @Input() typeFlight: string = 'Round Trip';
  @Output('returnData') returnData = new EventEmitter<InfoFlight>();

  MatIconRegistry = inject(MatIconRegistry);
  domSanitizer = inject(DomSanitizer);

  // ==============================

  // Initilizer and constructor Section

  // ==============================
  constructor(private cityService: CityService, private route: Router) {}

  ngOnInit() {
    this.MatIconRegistry.addSvgIcon(
      'triangle',
      this.domSanitizer.bypassSecurityTrustResourceUrl(
        '/assets/images/triangle.svg'
      )
    );
  }

  // That determine the state of the filed
  emptyField($event: FocusEvent | Event) {
    let inputValue = $event.srcElement as HTMLInputElement;
    if (inputValue.value === '') {
      inputValue.setAttribute('aria-expanded', 'false');
      inputValue.setAttribute('aria-selected', 'false');
    } else {
      inputValue.setAttribute('aria-expanded', 'true');
    }
  }

  //   TODO : Search by name of the country or the name of the cities

  // Helper function to check if a field in an object matches the location

  checkMatch(obj: any, fields: string[], location: string): boolean {
    for (let field of fields) {
      if (
        obj[field]?.toString().toLowerCase().includes(location.toLowerCase())
      ) {
        return true;
      }
    }
    return false;
  }

  searchCible($event: KeyboardEvent) {
    let result: string = ($event.srcElement as HTMLInputElement).value;

    // this from frontEnd json file

    if (
      result.trim() !== '' &&
      result !== null &&
      result !== undefined &&
      result.length > 3
    ) {
      this.resultSerach = [];
      this.cityService
        .getCity(result)
        .subscribe((response: City[] | string) => {
          if (response.length > 0 && typeof response !== 'string') {
            this.resultSerach = response
              .map((city) => {
                return `${city.name}, ${city.country.name}`;
              })
              .filter((city, index, self) => index === self.indexOf(city));
          }
        });
    }
  }

  setOrigin($event: MouseEvent) {
    this.Origin = ($event.srcElement as HTMLHeadElement).innerText;
    this.data.origin = this.Origin;
    this.inputOrigin?.nativeElement.setAttribute('aria-selected', 'true');
  }

  setDestination($event: MouseEvent) {
    this.Destination = ($event.srcElement as HTMLHeadElement).innerText;
    this.data.destination = this.Destination;
    this.inputDestination?.nativeElement.setAttribute('aria-selected', 'true');
  }

  calanderInfo($event: CalendarInfo) {
    let departureInput = this.departureDate?.nativeElement as HTMLInputElement;
    let arrivalInput = this.arrivalDate?.nativeElement as HTMLInputElement;
    if (this.typeFlight === 'Round trip') {
      if ($event.departure !== '' || $event.departure !== null) {
        departureInput.value = new Date($event.departure)
          .toDateString()
          .slice(0, -5);
        this.data.departureDate = new Date($event.departure);
        this.departureDate?.nativeElement.setAttribute(
          'aria-expanded',
          `${!(departureInput.value.trim() === '')}`
        );
      } else {
        departureInput.value = '';
      }

      if ($event.arrival === '' && $event.arrival === null) {
        arrivalInput.value = '';
      } else {
        arrivalInput.value = new Date($event.arrival)
          .toDateString()
          .slice(0, -5);
        this.data.returnDate = new Date($event.arrival);
        this.arrivalDate?.nativeElement.setAttribute(
          'aria-expanded',
          `${!(arrivalInput.value.trim() === '')}`
        );
      }
    } else {
      if ($event.departure !== '' || $event.departure !== null) {
        console.log($event);
        departureInput.value = new Date($event.departure)
          .toDateString()
          .slice(0, -5);
        this.data.departureDate = new Date($event.departure);
        this.departureDate?.nativeElement.setAttribute(
          'aria-expanded',
          `${!(departureInput.value.trim() === '')}`
        );
      } else {
        departureInput.value = '';
      }
    }
  }

  setActiveCalandar(departureDate: HTMLInputElement) {
    this.calendarActive = 'true';
    if (departureDate.getAttribute('aria-expanded') == 'false') {
      this.calendarActive = 'true';
    } else {
      this.calendarActive = 'false';
    }
  }

  setData($event: MouseEvent) {
    if (
      (this.data.departureDate != undefined &&
        this.data.returnDate != undefined &&
        this.data.origin != '') ||
      (this.data.origin != undefined && this.data.destination != '') ||
      this.data.destination != undefined
    ) {
      this.route.navigate(['flight-search/']);
      this.returnData.emit(this.data);
    }
  }
}
