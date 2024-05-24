import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { City } from '../../models/city.model';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class CityService {
  constructor(private http: HttpClient) {}
  private baseUrl = environment.baseUrl + environment.v2.mainLink;

  getCity(cityId: string): Observable<City[] | string> {
    return this.http.get<City[] | string>(
      `${this.baseUrl}${environment.v2.city.getByName}${cityId}`
    );
  }
  getAllCity(): Observable<City[] | string> {
    return this.http.get<City[] | string>(
      `${this.baseUrl}${environment.v2.city.allCities}`
    );
  }

  getCityByNameStateAndCountry(
    state: string,
    country: string
  ): Observable<string[] | string> {
    return this.http.get<string[] | string>(
      `${this.baseUrl}${environment.v2.city.getCitiesNameByCountryAndState}/${state},${country}`
    );
  }
}
