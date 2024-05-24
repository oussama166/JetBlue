package com.jetblue.jetblue_server.DOA.Modules.ENUMS;

public enum FlightCompany {
    AMERICAN_AIRLINES("American Airlines"),
    DELTA_AIR_LINES("Delta Air Lines"),
    UNITED_AIRLINES("United Airlines"),
    SOUTHWEST_AIRLINES("Southwest Airlines"),
    EMIRATES("Emirates"),
    LUFTHANSA("Lufthansa"),
    AIR_FRANCE("Air France"),
    BRITISH_AIRWAYS("British Airways"),
    QATAR_AIRWAYS("Qatar Airways"),
    CHINA_SOUTHERN_AIRLINES("China Southern Airlines"),
    ROYAL_AIR_MOROCCO("Royal Air Maroc");

    private final String companyName;

    FlightCompany(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }
}

