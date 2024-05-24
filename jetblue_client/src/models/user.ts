export class User {
  name: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  password?: string;
  gender: string;
  address: string;
  birthDay: string;
  city: string;
  state: string;
  country: string;
  isConnected: boolean;
  userId: number;
  token: string;
  reviewsList?: [];
  userNotifications?: [];

  constructor(
    name: string,
    lastName: string,
    email: string,
    phoneNumber: string,
    password: string,
    gender: string,
    address: string,
    birthDay: string,
    city: string,
    state: string,
    country: string,
    isConnected: boolean,
    userId: number,
    token: string,
    reviewsList: [],
    userNotifications: []
  ) {
    this.name = name;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.gender = gender;
    this.address = address;
    this.birthDay = birthDay;
    this.city = city;
    this.state = state;
    this.country = country;
    this.isConnected = isConnected;
    this.userId = userId;
    this.token = token;
    this.reviewsList = reviewsList;
    this.userNotifications = userNotifications;
  }
}
