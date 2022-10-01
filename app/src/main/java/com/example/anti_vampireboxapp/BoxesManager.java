package com.example.anti_vampireboxapp;

import com.example.anti_vampireboxapp.box.AntiVampBox;

import java.util.ArrayList;

public class BoxesManager {

    private static ArrayList<AntiVampBox> boxes = new ArrayList<>();



    public static ArrayList<AntiVampBox> getBoxes() {
        return boxes;
    }
    public static AntiVampBox getBox(int index) {
        return boxes.get(index);
    }

    public static void addBox(AntiVampBox box) {
        boxes.add(box);
    }
    public static void addAll(AntiVampBox... boxes) {
        for(AntiVampBox box : boxes) {
            addBox(box);
        }
    }

    public static boolean areAllBoxesEnabled() {
        return areAllBoxesEnabled(true);
    }
    public static boolean areAllBoxesDisabled() {
        return areAllBoxesEnabled(false);
    }
    private static boolean areAllBoxesEnabled(boolean enabled) {
        for(AntiVampBox box : boxes) {
            if(box.isCurrentEnabled() != enabled) {
                return false;
            }
        }
        return true;
    }

    public static void setCurrentAllBoxes(boolean enabled) {
        for(AntiVampBox box : boxes) {
            box.setCurrentEnabled(enabled);
        }
    }

}
