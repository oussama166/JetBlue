package com.jetblue.jetblue_server.DOA.Modules.ENUMS;

public enum Airplane {
    A320ceo("Airbus A320ceo"),
    B737_800("Boeing 737-800"),
    A321ceo("Airbus A321ceo"),
    B737_700("Boeing 737-700");

    private final String fullName;

    Airplane(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
