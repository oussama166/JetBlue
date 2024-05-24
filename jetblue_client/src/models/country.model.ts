export class Country {
  id: number;
  name: string;
  iso3: string;
  iso2: string;
  phoneCode: string;
  capital: string;
  currency: string;

  constructor(
    id: number,
    name: string,
    iso3: string,
    iso2: string,
    phoneCode: string,
    capital: string,
    currency: string
  ) {
    this.id = id;
    this.name = name;
    this.iso3 = iso3;
    this.iso2 = iso2;
    this.phoneCode = phoneCode;
    this.capital = capital;
    this.currency = currency;
  }
}
