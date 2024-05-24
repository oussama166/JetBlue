import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class CountryService {
  baseUrl = environment.baseUrl + environment.v2.mainLink;

  constructor(private http: HttpClient) {}

  getAllCountry(): Observable<string[] | string> {
    return this.http.get<string[] | string>(
      `${this.baseUrl}${environment.v2.country.getAllCitiese}`
    );
  }
  getAllCity(): Observable<string[] | string> {
    return this.http.get<string[] | string>(
      `${this.baseUrl}}${environment.v2.country.getAllCitiese}`
    );
  }
}
