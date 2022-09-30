package com.example.anti_vampireboxapp.box;

import androidx.annotation.NonNull;

public class AntiVampBox {

    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private boolean enabled = false;
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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
