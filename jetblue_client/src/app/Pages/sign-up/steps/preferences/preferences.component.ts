import { Component, EventEmitter, Input, Output } from '@angular/core';
import {
  FormGroup,
  FormGroupDirective,
  ReactiveFormsModule,
} from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, of } from 'rxjs';
import { User } from '../../../../../models/user';
import { InfoUserService } from '../../../../../services/infoUser/info-user.service';
import { InputComponent } from '../../../../Features/input/input.component';
import { AuthService } from '../../../../auth.service';

@Component({
  selector: 'app-preferences',
  standalone: true,
  templateUrl: './preferences.component.html',
  styleUrl: './preferences.component.scss',
  imports: [InputComponent, ReactiveFormsModule],
})
export class PreferencesComponent {
  // Input
  @Input() isDone: boolean = false;
  @Input() hidden: boolean = true;
  @Input() show!: boolean;

  // Ouput
  @Output() stepCounter = new EventEmitter<number>();

  // Local var
  sercurityInfo!: FormGroup;
  step: number = 3;

  constructor(
    private rootDirectiveForm: FormGroupDirective,
    private userService: InfoUserService,
    private authService: AuthService,
    private router: Router,
  ) {}
  ngOnInit() {
    this.sercurityInfo = this.rootDirectiveForm.form.get(
      'securityInfo'
    ) as FormGroup;
    console.log(this.rootDirectiveForm.form);
  }

  validateForm($event: MouseEvent) {
    this.isDone = this.sercurityInfo.status == 'VALID';
    if (this.isDone) {
      this.ValidateDataUser();
    }
  }

  getCountry() {
    return this.sercurityInfo.get('country')?.value;
  }

  isMatch() {
    let password = this.sercurityInfo.get('password');
    let confPassword = this.sercurityInfo.get('confirmPassword');
    if (
      password?.value['password'] !== confPassword?.value['confirmPassword']
    ) {
      password?.setErrors({
        invalid: true,
      });
      confPassword?.setErrors({
        invalid: true,
      });
    }
  }

  private ValidateDataUser() {
    const basic: any = Object.values(this.rootDirectiveForm.form.value)[0];
    const personal: any = Object.values(this.rootDirectiveForm.form.value)[1];
    const security: any = Object.values(this.rootDirectiveForm.form.value)[2];
    // Ensuring day and month are formatted correctly with leading zeros if necessary
    const formattedDay: string = String(basic['day']['day']).padStart(2, '0');
    const formattedMonth: string = String(basic['month']['month']).padStart(
      2,
      '0'
    );
    console.log(formattedDay, formattedMonth);

    const date: string[] = `${formattedDay}-${formattedMonth}`.split('-');
    console.log(date);
    const dayCheck = parseInt(date[0], 10); // Parse day as integer
    const monthCheck = parseInt(date[2], 10); // Parse month as integer
    const year = parseInt(date[1], 10); // Parse year as integer

    // Create a new Date object with the correct day, month, and year
    const parsedDate = new Date(year, monthCheck, dayCheck);
    const gender = (personal['gender']['gender'] as string).toUpperCase();

    // user object for test dummy data
    const user: User = {
      email: basic['email']['email'],
      name: basic['name']['name'],
      lastName: `${basic['lastName']['lastName']}`,
      phoneNumber: basic['phone']['phone'],
      password: security['password']['password'],
      address: personal['addresse']['addresse'],
      city: personal['city']['city'],
      state: personal['state']['state'],
      country: personal['country']['country'],
      birthDay: parsedDate.toISOString(),
      gender: gender,
      token: '',
      isConnected: false,
      userId: 1,
      reviewsList: [],
      userNotifications: [],
    };

    // Send user object to the service
    this.userService
      .setUser(user)
      .pipe(
        catchError((error) => {
          // Handle the error here
          console.error('Error occurred:', error);
          // Return a fallback value or rethrow the error
          return of(null); // Or use `throwError(error)` if you want to propagate the error
        })
      )
      .subscribe((data) => {
        if (data instanceof Object) {
          let token: string = (data as User).token?.toString();
          localStorage.setItem('Token', token);
          this.authService.CurrentUserSig.set(data);
          this.router.navigateByUrl('/');
        } else {
          // Handle the case when data is null (i.e., an error occurred)
          console.error('Login failed.');
        }
      });
  }
}
