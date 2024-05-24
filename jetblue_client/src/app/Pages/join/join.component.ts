import { NgIf } from '@angular/common';
import { Component, ElementRef, ViewChild } from '@angular/core';
import { MatIcon } from '@angular/material/icon';
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';

import { InfoUserService } from './../../../services/infoUser/info-user.service';
import { Router, RouterLink } from '@angular/router';
import {
  HttpBackend,
  HttpErrorResponse,
  HttpResponse,
  HttpStatusCode,
} from '@angular/common/http';

@Component({
  selector: 'app-join',
  standalone: true,
  imports: [
    NgIf,
    MatIcon,
    RouterLink,
    DialogModule,
    ButtonModule,
    InputTextModule,
  ],
  templateUrl: './join.component.html',
  styleUrl: './join.component.scss',
})
export class JoinComponent {
  // Variables
  holderEmail: string = '';
  EmailError: boolean | string = '';
  checkEmail: boolean = false;

  // View
  @ViewChild('email') emailRef!: ElementRef;
  visible: boolean = false;

  // Constructor
  constructor(
    private InfoUserService: InfoUserService,
    private router: Router
  ) {}
  // Functions
  validateEmail($event: FocusEvent) {
    const email = $event.target as HTMLInputElement;
    if (email.value === '' || !email.value.includes('@')) {
      email.setAttribute('aria-busy', 'true');
      this.EmailError = true;
      this.checkEmail = false;
    } else {
      email.setAttribute('aria-busy', 'false');
      this.EmailError = false;
      this.checkEmail = true;
      this.holderEmail = email.value;
    }
  }
  join() {
    let email = this.emailRef.nativeElement as HTMLInputElement;

    // we need to check if the email not used before
    this.InfoUserService.checkEmail(this.holderEmail).subscribe(
      (response) => {},
      (error: HttpErrorResponse) => {
        if (error.status === 400) {
          email.setAttribute('aria-busy', 'false');
          this.EmailError = true;
          this.checkEmail = false;
          email.value = '';
          this.visible = true;
          console.log('Error checking email:', error);
        }
        if(error.status === 200){
          this.InfoUserService.setData(this.holderEmail);
          this.router.navigate(['/enroll/singUp']);
        }
      },
    );
  }
}
