package com.example.wsr_tren.OtdelAndBank;

import androidx.annotation.NonNull;

public class RecyclerOtdelBank {

    private final String Street;
    private final String State;
    private final String OpeningHours;


    public RecyclerOtdelBank(String street, String state, String openingHours) {
        Street = street;
        State = state;
        OpeningHours = openingHours;
    }

    public String getStreet() {
        return Street;
    }

    public String getState() {
        return State;
    }

    public String getOpeningHours() {
        return OpeningHours;
    }


}
