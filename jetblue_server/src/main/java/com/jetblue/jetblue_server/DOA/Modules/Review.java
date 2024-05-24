package com.jetblue.jetblue_server.DOA.Modules;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ReviewId;
    @Min(0)
    @Max(5)
    private float Rating;
    private String Comment;
    @Builder.Default
    private Date DateReview = new Date();

    @ManyToOne
    private User userId;

    @ManyToOne
    private FlightBooking flightBooking;


}
