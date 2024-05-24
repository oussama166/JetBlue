export interface FlightWay {
  value: string;
  viewValue: string;
}

export interface InfoFlight {
  flightType?: string;
  origin?: string;
  destination?: string;
  departureDate?: Date;
  returnDate?: Date;
  passengers: Passanger;
}

export interface Passanger {
  adult: number | 1;
  children: number | 0;
  LapInfant: number | 0;
  total?: number;
}

export interface Country {
  id: number;
  name: string | null;
  iso3: string | null;
  iso2: string | null;
  numeric_code: string | null;
  phone_code: string | null;
  capital: string | null;
  currency: string | null;
  currency_name: string | null;
  currency_symbol: string | null;
  tld: string | null;
  native: string | null;
  region: string | null;
  region_id: string | null;
  subregion: string | null;
  subregion_id: string | null;
  nationality: string | null;
  timezones: Timezone[] | null;
  translations: { [key: string]: string } | null;
  latitude: string | null;
  longitude: string | null;
  emoji: string | null;
  emojiU: string | null;
  states: State[];
}

export interface Timezone {
  zoneName: string | null;
  gmtOffset: number | null;
  gmtOffsetName: string | null;
  abbreviation: string | null;
  tzName: string | null;
}

export interface State {
  id: number | null;
  name: string | null;
  state_code: string | null;
  latitude: string | null;
  longitude: string | null;
  type: string | null;
  cities: City[];
}

export interface City {
  id: number | null;
  name: string | null;
  latitude: string | null;
  longitude: string | null;
}

export interface CalendarInfo {
  departure: string | Date;
  arrival: string | Date;
}

export interface LoginInterface {
  email: string;
  password: string;
}

export interface signUpData {
  title: string;
  description: string;
}

export type CalendarType = 'single' | 'multiple' | 'range';

export interface UserTokenAcces {
  address: string;
  birthDay: [number, number, number]; // Tuple representing year, month, day
  city: string;
  country: string;
  email: string;
  gender: string;
  lastName: string;
  name: string;
  phoneNumber: string;
  state: string;
  token: string;
  userId: number;
}
