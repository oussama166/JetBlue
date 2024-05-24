import { NgIf, NgOptimizedImage } from '@angular/common';
import { Component, ElementRef, Inject, ViewChild } from '@angular/core';
import { MatDividerModule } from '@angular/material/divider';
import { MatIcon } from '@angular/material/icon';
import { Router } from '@angular/router';
import { BadgeModule } from 'primeng/badge';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [MatIcon, NgOptimizedImage, MatDividerModule, BadgeModule, NgIf],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss',
})
export class NavbarComponent {
  LogOut() {
    localStorage.removeItem('Token');
    this.authService.setCurrentUser(null);
    this.router.navigate(['/']); // Redirect to home or login page
  }
  constructor(private router: Router, public authService: AuthService) {}

  get currentUser() {
    return this.authService.CurrentUserSig();
  }

  // this is the cart counter that will be displayed on the cart icon
  cartCounter: number = 0;
  addToCart() {
    this.cartCounter++;
  }

  // We need to create ViewChild for the dropdown menu
  @ViewChild('dropdown1') dropdown1: ElementRef<HTMLHeadingElement> | undefined;
  @ViewChild('dropdown2') dropdown2: ElementRef<HTMLHeadingElement> | undefined;
  //NOTE: This for loop is used to loop through the dropdowns and set the aria-expanded attribute to true or false based on the index of the dropdown.
  clicker(event: MouseEvent, index: number) {
    const eventTarget = document.querySelectorAll('#items');
    const dropdowns = [this.dropdown1, this.dropdown2];

    dropdowns.forEach((dropdown, idx) => {
      console.log(idx);
      const isActive = idx === index;
      const ariaExpanded = isActive
        ? String(
            dropdown?.nativeElement.getAttribute('aria-expanded') !== 'true'
          )
        : 'false';
      eventTarget[idx].setAttribute('aria-active', String(ariaExpanded));
      dropdown?.nativeElement.setAttribute('aria-expanded', ariaExpanded);
    });
  }
  goToJoinUs() {
    this.router.navigate(['enroll/join_us']);
  }
  goToLogin() {
    this.router.navigate(['enroll/signIn']);
  }
}
