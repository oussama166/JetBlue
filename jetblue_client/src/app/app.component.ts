import { NgIf } from '@angular/common';
import { Component, inject } from '@angular/core';
import { MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';
import { Router, RouterOutlet } from '@angular/router';
import { FooterComponent } from './footer/footer.component';
import { NavbarComponent } from './navbar/navbar.component';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
  imports: [RouterOutlet, NavbarComponent, FooterComponent, NgIf],
})
export class AppComponent {
  title = 'jetblue_client';
  isSignInRoute: boolean = false;

  INGNORE_GROUP: string[] = [
    '/enroll/signIn',
    '/enroll/join_us',
    '/enroll/singUp',
  ];

  MatIconRegistry = inject(MatIconRegistry);
  domSanitizer = inject(DomSanitizer);

  constructor(private router: Router) {}

  ngOnInit() {
    this.router.events.subscribe((event) => {
      for (let key of this.INGNORE_GROUP) {
        if (this.router.url.includes(key)) {
          this.isSignInRoute = true;
          console.log(this.isSignInRoute);
          return;
        }
        this.isSignInRoute = false;
        console.log(this.isSignInRoute);
      }
    });
    this.MatIconRegistry.addSvgIcon(
      'search',
      this.domSanitizer.bypassSecurityTrustResourceUrl(
        '/assets/images/search.svg'
      )
    );
    this.MatIconRegistry.addSvgIcon(
      'triangle',
      this.domSanitizer.bypassSecurityTrustResourceUrl(
        'assets/images/triangle.svg'
      )
    );
    this.MatIconRegistry.addSvgIcon(
      'EyeOn',
      this.domSanitizer.bypassSecurityTrustResourceUrl(
        'assets/images/VisiblityOn.svg'
      )
    );
    this.MatIconRegistry.addSvgIcon(
      'EyeOff',
      this.domSanitizer.bypassSecurityTrustResourceUrl(
        'assets/images/VisiblityOff.svg'
      )
    );
    // Arrow
    this.MatIconRegistry.addSvgIcon(
      'ArrowL',
      this.domSanitizer.bypassSecurityTrustResourceUrl(
        'assets/images/arrow_left.svg'
      )
    );
    this.MatIconRegistry.addSvgIcon(
      'ArrowR',
      this.domSanitizer.bypassSecurityTrustResourceUrl(
        'assets/images/arrow_right.svg'
      )
    );


  }
}
