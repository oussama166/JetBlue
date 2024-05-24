import { NgFor, NgIf } from '@angular/common';
import {
  Component,
  EventEmitter,
  Input,
  Output,
  SimpleChange,
  SimpleChanges,
} from '@angular/core';
import {
  FormControl,
  FormGroupDirective,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { RadioButtonModule } from 'primeng/radiobutton';

import { TooltipOptions } from 'primeng/api';
import { InputTextModule } from 'primeng/inputtext';
import { TooltipModule } from 'primeng/tooltip';
import { CountryService } from '../../../services/Country/country.service';
import { StateService } from '../../../services/State/state.service';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-input',
  standalone: true,
  imports: [
    NgFor,
    NgIf,
    ReactiveFormsModule,
    RadioButtonModule,
    FormsModule,
    TooltipModule,
    InputTextModule,
    MatIconModule,
  ],
  templateUrl: './input.component.html',
  styleUrl: './input.component.scss',
})
export class InputComponent {
  @Input() Label: string = '';
  @Input() Type:
    | 'text'
    | 'email'
    | 'password'
    | 'Phone'
    | 'Month'
    | 'Day'
    | 'Year'
    | 'Gender'
    | 'City'
    | 'State'
    | 'Country'
    | 'Password' = 'text';
  @Input() ErrorMessage: string = '';
  @Input() Value: string = '';
  @Input() IsOptional: boolean = false;
  @Input() FormControlName!: string;
  @Input() disable: boolean = false;
  @Input() STATE_COUNTRY: Object = {};
  @Input() isFocus: boolean = false;
  @Input() setError: boolean = false;

  @Output() CityOnCountry = new EventEmitter<string>();
  ingredient!: string;
  EmailError: boolean | string = '';
  checkEmail: boolean = false;
  passwordValid!: boolean;
  holderEmail: string = '';
  listRegex: RegExp[] = [
    /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/,
    /^[+]?[(]?[0-9]{2,3}[)]?[-\s.]?[0-9]{3}[-\s.]?[0-9]{4,6}$/,
    /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&_])[A-Za-z\d$@$!%*?&_]{5,20}$/,
  ];
  Country: string[] = [];
  City: string[] = [];
  State: string[] = [];
  control!: FormControl;
  tooltipOptions!: TooltipOptions;
  toggleOffOn: boolean = false;

  /**
   * create form control for all the state of the input
   */

  // Ng On Init constructor
  constructor(
    private GroupFormRoot: FormGroupDirective,
    private countryService: CountryService,
    private stateService: StateService
  ) {}

  ngOnInit() {
    this.control = this.GroupFormRoot.control.get(
      this.FormControlName
    ) as FormControl;
    if (this.isFocus) {
      this.control.markAllAsTouched;
    }
    // Contry service fetching
    if (this.Type == 'Country') {
      this.countryService
        .getAllCountry()
        .subscribe((data: string[] | string) => {
          if (Array.isArray(data)) {
            this.Country = data;
          }
        });
    }
    if (this.Type == 'State') {
      let countryState: string = Object.values(this.STATE_COUNTRY)[0];
      this.stateService
        .getState(countryState)
        .subscribe((data: string[] | string) => {
          if (Array.isArray(data)) this.State = data;
        });
    }
    if (this.Type == 'Password') {
      this.tooltipOptions = {
        showDelay: 150,
        tooltipEvent: 'focus',
        tooltipPosition: 'right',
        tooltipLabel: `At least one lowercase letter \nAt least one uppercase letter\nAt least one digit\nAt least one special character from the set [$@$!%*?&_]\nPassword length between 5 and 20 characters `,
        tooltipStyleClass: 'min-w-[400px]',
      };
    }
  }

  ngOnChanges(changes: SimpleChanges) {
    if (this.Type === 'State') {
      const stateCountryChanges = changes['STATE_COUNTRY'] as SimpleChange;
      if (stateCountryChanges) {
        let curVal = Object.values(
          stateCountryChanges.currentValue
        )[0] as string;
        this.getDataState(curVal);
      }
    }
  }

  validateInput($event: FocusEvent | Event) {
    const text = $event.target as HTMLInputElement;
    const inputValue = text.value.trim();

    if (!this.IsOptional) {
      if (this.Type === 'email') {
        if (inputValue === '' || !this.listRegex[0].test(inputValue)) {
          this.setInvalidState(text);
        } else {
          this.setValidState(text, inputValue);
        }
      } else if (this.Type === 'Phone') {
        if (inputValue === '' || !this.listRegex[1].test(inputValue)) {
          this.setInvalidState(text);
        } else {
          this.setValidState(text, inputValue);
        }
      } else if (this.Type == 'password') {
        if (this.allowSecutiyCode(inputValue)) {
          this.setInvalidState(text);
        } else {
          this.setValidState(text, inputValue);
        }
      } else {
        if (inputValue === '') {
          this.setInvalidState(text);
        } else {
          this.setValidState(text, inputValue);
        }
      }
    }
  }

  changeValue($event: Event) {
    if (this.Type === 'Country') {
      this.CityOnCountry.emit(($event.target as HTMLInputElement).value);
    }
    const inputValue = ($event.target as HTMLInputElement).value;
    this.control.setValue({ [this.FormControlName]: inputValue });
  }

  setGender(gender: string) {
    this.control.setValue({ gender: gender });
  }

  private setInvalidState(inputElement: HTMLInputElement) {
    inputElement.setAttribute('aria-busy', 'true');
    this.EmailError = true;
    this.checkEmail = false;
  }

  private setValidState(inputElement: HTMLInputElement, emailValue: string) {
    inputElement.setAttribute('aria-busy', 'false');
    this.EmailError = false;
    this.checkEmail = true;
    this.holderEmail = emailValue;
  }

  private getDataState(countryName: string) {
    this.stateService
      .getState(countryName)
      .subscribe((data: string[] | string) => {
        if (Array.isArray(data)) {
          this.State = data;
          console.log(data);
        }
      });
  }

  /**
   * At least 12 characters long but 14 or more is better |
   * A combination of uppercase letters, lowercase letters, numbers, and symbols<br/>
   * Not a word that can be found in a dictionary or the name of a person, character, product, or organization<br/>
   * Significantly different from your previous passwords<br/>
   * Easy for you to remember but difficult for others to guess. Consider using a memorable phrase like "6MonkeysRLooking^"<br/>
   * @param password
   * @returns `Bool`
   */

  allowSecutiyCode(password: string): boolean {
    let isPasswordValid = this.listRegex[2].test(password);

    console.log('Password: ' + password + ', Is valid: ' + isPasswordValid);

    // Return false if either password or email is invalid
    this.passwordValid = !isPasswordValid;

    return isPasswordValid;
  }

  VisibleOnOff() {
    this.toggleOffOn = !this.toggleOffOn;
  }
}
