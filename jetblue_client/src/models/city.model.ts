import { Country } from './country.model';

export class City {
  id: number;
  name: string;
  stateCode: string;
  countryCode: string;
  latitude: number;
  longitude: number;
  flag: boolean;
  wikiDataId: string;
  state: string;
  country: Country;

  constructor(
    id: number,
    name: string,
    stateCode: string,
    countryCode: string,
    latitude: number,
    longitude: number,
    flag: boolean,
    wikiDataId: string,
    state: string,
    country: Country
  ) {
    this.id = id;
    this.name = name;
    this.stateCode = stateCode;
    this.countryCode = countryCode;
    this.latitude = latitude;
    this.longitude = longitude;
    this.flag = flag;
    this.wikiDataId = wikiDataId;
    this.state = state;
    this.country = country;
  }
}
