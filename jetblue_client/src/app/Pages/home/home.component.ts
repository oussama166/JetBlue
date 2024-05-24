import {Component, ElementRef, inject, ViewChild} from '@angular/core';
import {MatIconModule, MatIconRegistry} from '@angular/material/icon';
import {DomSanitizer} from '@angular/platform-browser';
import {MatSelectModule} from '@angular/material/select';
import {NgClass, NgForOf, NgIf} from '@angular/common';
import {FlightWay, InfoFlight} from "../../../utils/types";
import {PoopusComponent} from "../../Features/poopus/poopus.component";
import {data} from "autoprefixer";
import {FlightOneWayComponent} from "../../Features/Flight/flight-one-way/flight-one-way.component";
import {CalanderRangeComponent} from "../../Features/calander-range/calander-range.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MatIconModule, MatSelectModule, NgForOf, NgClass, PoopusComponent, FlightOneWayComponent, NgIf, CalanderRangeComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent {

  // Return Date
  returnDate: Date = new Date();

  // We need to get the data from the child elements {popups, flight reservation}
  infoFlight: InfoFlight = {
    flightType: '',
    destination: '',
    origin: '',
    departureDate: new Date(),
    returnDate: new Date(),
    passengers: {
      adult: 1,
      children: 0,
      LapInfant: 0,
      total: 0
    },
  };


  ngOnInit() {
    this.returnDate.setDate(this.returnDate.getDate() + 2);
    this.infoFlight = {
      flightType: '',
      destination: '',
      origin: '',
      departureDate: new Date(),
      returnDate: this.returnDate,
      passengers: {
        adult: 1,
        children: 0,
        LapInfant: 0,
        total: 0
      },
    };
  }

  getDataFromPopups(data: InfoFlight) {
    this.infoFlight = data;
    console.log(this.infoFlight)
  }


  MatIconRegistry = inject(MatIconRegistry);
  domSanitizer = inject(DomSanitizer);


  constructor() {
    this.MatIconRegistry.addSvgIcon(
      'plane',
      this.domSanitizer.bypassSecurityTrustResourceUrl(
        'assets/images/plane.svg'
      )
    );
    this.MatIconRegistry.addSvgIcon(
      'flight+cars',
      this.domSanitizer.bypassSecurityTrustResourceUrl(
        'assets/images/flight+cars.svg'
      )
    );
    this.MatIconRegistry.addSvgIcon(
      'flight+hotel',
      this.domSanitizer.bypassSecurityTrustResourceUrl(
        'assets/images/flight+hotel.svg'
      )
    );
    this.MatIconRegistry.addSvgIcon(
      'Cars',
      this.domSanitizer.bypassSecurityTrustResourceUrl('assets/images/cars.svg')
    );
  }


  getReturnData($event: InfoFlight) {
    console.log($event)
  }
}
