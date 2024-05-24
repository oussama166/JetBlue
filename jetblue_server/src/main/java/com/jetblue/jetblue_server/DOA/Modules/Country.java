package com.jetblue.jetblue_server.DOA.Modules;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;


import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String iso3;

    private String numericCode;

    private String iso2;

    private String phoneCode;

    private String capital;

    private String currency;

    private String currencyName;

    private String currencySymbol;

    private String tld;

    private String nativeName;


    @ManyToOne(optional = true)  // Country may not have a region
    @JoinColumn(name = "region_id")
    @JsonBackReference
    private Region region;

    @ManyToOne(optional = true)  // Country may not have a subregion
    @JoinColumn(name = "subregion_id")
    @JsonBackReference
    private Subregion subregion;

    @OneToMany(mappedBy = "country")
    @JsonBackReference
    private List<State> states;

    private String nationality;

    private String timezones;

    private String translations;

    private Double latitude;

    private Double longitude;

    private String emoji;

    private String emojiU;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    private Boolean flag;

    private String wikiDataId;
}