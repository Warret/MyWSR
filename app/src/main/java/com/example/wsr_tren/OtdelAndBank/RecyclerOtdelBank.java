package com.example.wsr_tren.OtdelAndBank;

import androidx.annotation.NonNull;

public class RecyclerOtdelBank {

    private final String Street;
    private final String State;
    private final String Time;


    public RecyclerOtdelBank(String street, String state, String time) {
        Street = street;
        State = state;
        Time = time;
    }

    public String getStreet() {
        return Street;
    }

    public String getState() {
        return State;

    }


    public String getTime() {
        return Time;
    }
}
