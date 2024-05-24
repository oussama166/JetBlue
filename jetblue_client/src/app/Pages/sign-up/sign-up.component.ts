import { Component, ElementRef, ViewChild } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

import { CommonModule } from '@angular/common';
import { InfoUserService } from '../../../services/infoUser/info-user.service';
import { signUpData } from '../../../utils/types';
import { InputComponent } from '../../Features/input/input.component';
import { BasicComponent } from './steps/basic/basic.component';
import { PersonalComponent } from './steps/personal/personal.component';
import { PreferencesComponent } from './steps/preferences/preferences.component';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.scss',
  imports: [
    InputComponent,
    BasicComponent,
    FormsModule,
    CommonModule,
    ReactiveFormsModule,
    PersonalComponent,
    PreferencesComponent,
  ],
})
export class SignUpComponent {
  @ViewChild('stepOne') stepOne!: ElementRef;
  @ViewChild('stepTwo') stepTwo!: ElementRef;
  @ViewChild('stepThree') stepThree!: ElementRef;
  name: string = '';
  // formBuilder
  signUpForm!: FormGroup;
  // validation for first form
  email: string = '';
  // steps
  counter: number = 1;
  // Data
  StaticData: signUpData[] = [
    {
      title: 'Sign Up',
      description: 'Let’s get started with the basics.',
    },
    {
      title: 'Personal Info',
      description:
        'First we’ll ask you questions to comply with TSA guidelines.',
    },
    {
      title: 'Security Info',
      description: 'Create password and crredential info',
    },
  ];

  constructor(private infoUser: InfoUserService, private fb: FormBuilder) {
    // to get email from the observable
    this.infoUser.getData().subscribe((data) => (this.email = data));
    console.log(this.email);
    // creation of group form builder
    this.signUpForm = this.fb.group({
      basic: this.fb.group({
        name: ['', Validators.required],
        middleName: [''],
        lastName: ['', Validators.required],
        email: ['', Validators.required],
        day: ['', Validators.required],
        month: ['', Validators.required],
        phone: ['', Validators.required],
      }),
      personalInfo: this.fb.group({
        gender: ['', Validators.required],
        addresse: ['', Validators.required],
        addresse2: [''],
        city: ['', Validators.required],
        state: [''],
        zip: ['', Validators.required],
        country: [''],
      }),
      securityInfo: this.fb.group({
        password: ['', Validators.required],
        confirmPassword: ['', Validators.required],
      }),
    });
  }

  getCounter($event: number) {
    this.counter = $event;
  }
  submitData(){
  }
}
