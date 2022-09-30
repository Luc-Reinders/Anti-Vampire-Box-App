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

}
