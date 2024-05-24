import { Routes } from '@angular/router';
import { HomeComponent } from './Pages/home/home.component';
import { SignInComponent } from './Pages/sign-in/sign-in.component';
import { NotSignInGuard } from '../utils/SignInGuard';
import { JoinComponent } from './Pages/join/join.component';
import { SignUpComponent } from './Pages/sign-up/sign-up.component';
import { NotFoundComponent } from './Pages/not-found/not-found.component';
import { FlightSearchComponent } from './Pages/flight-search/flight-search.component';

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    title: 'Home pages',
  },
  {
    path: 'enroll/signIn',
    component: SignInComponent,
    canActivate: [NotSignInGuard],
    title: 'Sign In',
  },
  {
    path: 'enroll/join_us',
    component: JoinComponent,
    canActivate: [NotSignInGuard],
    title: 'Join Us',
  },
  {
    path: 'enroll/singUp',
    component: SignUpComponent,
    canActivate: [NotSignInGuard],
    title: 'Sign Up',
  },
  {
    path: 'flight-search',
    component: FlightSearchComponent,
    title: 'flight saerch',
  },
  {
    path: 'not-found',
    component: NotFoundComponent,
    title: 'Not Found',
  },
  {
    path: '**',
    redirectTo: '/not-found',
    pathMatch: 'full',
    title: 'Not Found',
  },
];
