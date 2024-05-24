package com.jetblue.jetblue_server.DOA.Modules;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarRental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CarRentalId;
    private String CarModel;
    private String RegistrationNumber;
    private Date PickUpDate;
    private Date DropOffDate;
    private String RentalLocation;
    @OneToOne
    private FlightBooking flightBooking;
}
