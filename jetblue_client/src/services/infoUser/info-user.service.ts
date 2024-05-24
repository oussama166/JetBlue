import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../models/user';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class InfoUserService {
  protected _infoUser: Observable<string> = new Observable();
  private URL = environment.baseUrl + environment.v1.mainLink;

  constructor(private http: HttpClient) {}

  // This for setting the email info from the join us page to the sign up page
  setData(data: string) {
    this._infoUser = new Observable((observer) => {
      observer.next(data);
    });
  }
  // This for getting the email info from the join us page to the sign up page
  getData(): Observable<string> {
    return this._infoUser;
  }

  // Login to the system
  login(email: string, password: string): Observable<User | string> {
    return this.http.post<User | string>(
      `${this.URL}${environment.v1.user.login}`,
      {
        Email: email,
        Password: password,
      }
    );
  }

  // Register a new user
  setUser(User: User): Observable<User | string> {
    return this.http.post<User | string>(
      `${this.URL}${environment.v1.user.newUser}`,
      User
    );
  }

  // check if the email is already exist
  checkEmail(email: string): Observable<string> {
    return this.http.get<string>(
      `${this.URL}${environment.v1.user.emailExist}/${email}`
    );
  }
}
