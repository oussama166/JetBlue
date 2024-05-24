package com.jetblue.jetblue_server.DOA.Modules;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ident;
    private String type;
    private String name;
    private double latitude_deg;
    private double longitude_deg;
    private String continent;
    private String iso_country;
    private String iso_region;
    private String municipality;
}
