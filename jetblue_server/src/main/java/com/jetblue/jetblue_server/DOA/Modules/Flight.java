package com.jetblue.jetblue_server.DOA.Modules;

import com.jetblue.jetblue_server.DOA.Modules.ENUMS.Airplane;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int FlightId;

    private LocalDateTime DepartureTime;
    private LocalDateTime ArrivalTime;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn
    @Fetch(FetchMode.JOIN)
    private Airports Origin;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn
    private Airports Destination;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Airplane Plane;

    @Column(nullable = false)
    private String ArrivalTerminal;
    @Column(nullable = false)
    private String DepartureTerminal;
    @Min(0)
    private int numberPlace;


}
