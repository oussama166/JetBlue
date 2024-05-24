export const environment = {
  production: false,
  baseUrl: 'http://localhost:8090/',
  v2: {
    mainLink: 'api/v2/',
    city: {
      getByName: 'getByName?nameCountry=',
      allCities: 'allCities',
      getCitiesNameByCountryAndState: 'getCitiesNameByCountryAndState',
    },
    country: {
      getAllCitiese: 'getAllCities',
      allCities: 'allCities',
    },
    state: {
      getStateByCountry: 'getStateByCountry',
    },
    airport: {
      getAirports: 'getAirports',
      getAirport: 'getAirport/',
      getAirportsCity: 'getAirports/',
      getAirportsCountry: 'getAirportsCountry/',
    },
  },
  v1: {
    mainLink: 'api/v1/',
    user: {
      login: 'login',
      newUser: 'newUser',
      emailExist: 'emailExist',
    },
  },
};
