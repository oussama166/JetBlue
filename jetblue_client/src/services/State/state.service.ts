import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class StateService {
  private baseUrl = environment.baseUrl + environment.v2.mainLink;

  constructor(private http: HttpClient) {}

  getState(countryName: string): Observable<string[] | string> {
    return this.http.get<string[] | string>(
      `${this.baseUrl}${environment.v2.state.getStateByCountry}/${countryName}`
    );
  }
}
