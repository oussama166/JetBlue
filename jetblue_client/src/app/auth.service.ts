import { Injectable, signal } from "@angular/core";
import { User } from "../models/user";

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  CurrentUserSig = signal<User | null>(null);

  setCurrentUser(user: User | null) {
    this.CurrentUserSig.set(user); // Use .set() to update the signal value
  }
}
