package com.example.anti_vampireboxapp;

import com.example.anti_vampireboxapp.box.AntiVampBox;

import java.util.ArrayList;

public class BoxesManager {

    private static ArrayList<AntiVampBox> boxes = new ArrayList<>();

    public ArrayList<AntiVampBox> getBoxes() {
        return boxes;
    }
    public AntiVampBox getBox(int index) {
        return boxes.get(index);
    }

    public void addBox(AntiVampBox box) {
        boxes.add(box);
    }
    public void addAll(AntiVampBox... boxes) {
        for(AntiVampBox box : boxes) {
            this.addBox(box);
        }
    }
}
