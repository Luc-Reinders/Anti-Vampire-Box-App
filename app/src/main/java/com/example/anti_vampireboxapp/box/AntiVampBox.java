package com.example.anti_vampireboxapp.box;

import androidx.annotation.NonNull;

public class AntiVampBox {

    String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public AntiVampBox(String name) {
        setName(name);
    }


    @NonNull
    @Override
    public String toString() {
        return getName();
    }
}
