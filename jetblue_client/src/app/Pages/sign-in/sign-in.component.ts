import { Component, ElementRef, inject, ViewChild } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatIcon, MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';
import { Router, RouterLink } from '@angular/router';
import { CardModule } from 'primeng/card';
import { DividerModule } from 'primeng/divider';
import { catchError, of } from 'rxjs';
import { User } from '../../../models/user';
import { InfoUserService } from '../../../services/infoUser/info-user.service';
import { LoginInterface } from '../../../utils/types';
import { AuthService } from '../../auth.service';

@Component({
  selector: 'app-sign-in',
  standalone: true,
  imports: [
    CardModule,
    DividerModule,
    MatIcon,
    ReactiveFormsModule,
    RouterLink,
  ],
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.scss',
})
export class SignInComponent {
  @ViewChild('email') email: ElementRef<HTMLDivElement> | undefined;
  @ViewChild('password') password: ElementRef<HTMLDivElement> | undefined;
  // Create Form Control
  SingInForm: LoginInterface = {
    email: '',
    password: '',
  };

  MatIconRegistry = inject(MatIconRegistry);
  domSanitizer = inject(DomSanitizer);

  constructor(
    private userServices: InfoUserService,
    private authService: AuthService,
    private router: Router
  ) {
    this.MatIconRegistry.addSvgIcon(
      'triangle',
      this.domSanitizer.bypassSecurityTrustResourceUrl(
        'assets/images/triangle.svg'
      )
    );
  }
  ngOnInit(): void {
    this.SingInForm = {
      email: '',
      password: '',
    };
  }

  insertDataEmail($event: KeyboardEvent) {
    this.SingInForm.email = ($event.srcElement as HTMLInputElement).value;
  }

  insertDataPassword($event: KeyboardEvent) {
    this.SingInForm.password = ($event.srcElement as HTMLInputElement).value;
  }

  showResult() {
    this.userServices
      .login(this.SingInForm.email, this.SingInForm.password)
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
