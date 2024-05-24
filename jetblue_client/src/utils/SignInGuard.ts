import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class NotSignInGuard implements CanActivate {
  INGNORE_GROUP: string[] = [
    '/enroll/signIn',
    '/enroll/join_us',
    '/enroll/singUp',
  ];
  constructor(private router: Router) {}

  canActivate(): boolean {
    const currentPath = window.location.pathname;
    for (const path of this.INGNORE_GROUP) {
      if (currentPath.includes(path)) {
        return true;
      }
    }
    this.router.navigate(['/not-found']); // Redirect to the not-found page for other paths
    return false;
  }
}
