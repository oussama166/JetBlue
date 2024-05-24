import {Component, ElementRef, EventEmitter, Output, ViewChild} from '@angular/core';
import {MatIcon} from "@angular/material/icon";
import {NgClass, NgForOf} from "@angular/common";
import {FlightWay, InfoFlight} from "../../../utils/types";

@Component({
  selector: 'app-poopus',
  standalone: true,
  imports: [
    MatIcon,
    NgClass,
    NgForOf
  ],
  templateUrl: './poopus.component.html',
  styleUrl: './poopus.component.scss'
})
export class PoopusComponent {
  @ViewChild('Dropdown2') dropdown2: ElementRef | undefined;
  @ViewChild('Dropdown1') dropdown1: ElementRef | undefined;
  // This holds the message the passengers total
  data: string = "";
  @Output() getFlightTypePassengers = new EventEmitter<InfoFlight>();

  infoFlight: InfoFlight = {
    flightType: '',
    passengers: {
      adult: 0,
      children: 0,
      LapInfant: 0,
    },
  };

  flightWay: FlightWay[] = [
    {
      value: 'roundTrip',
      viewValue: 'Round trip',
    },
    {
      value: 'oneWay',
      viewValue: 'One way',
    },
    {
      value: 'multiCity',
      viewValue: 'Multi-city',
    },
  ];


  // on Init the hone component
  ngOnInit() {
    this.infoFlight = {
      flightType: 'Round trip',
      passengers: {
        adult: 1,
        children: 0,
        LapInfant: 0,
        total: 0,
      },
    };
    this.updateDate();
    this.getFlightTypePassengers.emit(this.infoFlight);
  }

  toggleDropdown(div: HTMLDivElement) {
    div.classList.toggle('hidden');
  }

  // Increment passengers info
  Mins(type: string) {
    if (type == 'adult') {
      this.infoFlight.passengers.adult > 0 &&
      this.infoFlight.passengers.adult--;
    } else if (type == 'children') {
      this.infoFlight.passengers.children > 0 &&
      this.infoFlight.passengers.children--;
    } else if (type == 'LapInfant') {
      this.infoFlight.passengers.LapInfant > 0 &&
      this.infoFlight.passengers.LapInfant--;
    }
    this.updateDate();
  }

  Plus(type: string) {
    if (type == 'adult') {
      (this.infoFlight.passengers.adult >= 0 || this.infoFlight.passengers.adult < 7) &&
      this.infoFlight.passengers.adult++;
    } else if (type == 'children') {
      (this.infoFlight.passengers.children >= 0 && this.infoFlight.passengers.children < 3) &&
      this.infoFlight.passengers.children++;
    } else if (type == 'LapInfant') {
      this.infoFlight.passengers.LapInfant >= 0 &&
      this.infoFlight.passengers.LapInfant++;
    }
    this.updateDate();
  }


  // drop down the select box
  selectFlightWay(flightType: string) {
    this.infoFlight.flightType = flightType;
    if (
      flightType == 'One way' ||
      flightType == 'Round trip' ||
      flightType == 'Multi-city'
    ) {
      this.dropdown1?.nativeElement.classList.add('hidden');
      this.getFlightTypePassengers.emit(this.infoFlight);
    }
  }


  showData() {
    // Extract the passenger fields
    const {children, LapInfant, adult} = this.infoFlight.passengers;
    let {total} = this.infoFlight.passengers;
    let resultReturn = "";

    // Check for different passenger types and construct the result string accordingly
    if (adult > 0) {
      resultReturn += adult + " Adult" + (adult > 1 ? "s" : ""); // Add 'Adult' or 'Adults' based on the count
    }

    if (children > 0) {
      resultReturn += (resultReturn.length > 0 ? ", " : "") + children + " Child" + (children > 1 ? "ren" : ""); // Add 'Child' or 'Children' based on the count

    }

    if (LapInfant > 0) {
      resultReturn += (resultReturn.length > 0 ? ", " : "") + LapInfant + " Lap Infant" + (LapInfant > 1 ? "s" : ""); // Add 'Lap Infant' or 'Lap Infants' based on the count
    }


    for (let i = 0; i < Object.values(this.infoFlight.passengers).length - 1; i++) {
      total += Object.values(this.infoFlight.passengers)[i];
    }
    if(adult == 0){
      this.infoFlight.passengers.LapInfant = 0;
    }
    this.getFlightTypePassengers.emit(this.infoFlight);
    return resultReturn;
  }

  updateDate() {
    this.data = this.showData();
  }

}





