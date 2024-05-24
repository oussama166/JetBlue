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
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PaymentId;
    @OneToOne
    private FlightBooking flightBooking;
    private double Amount;
    private Date PaymentDate;
    private String PaymentStatus;

}
