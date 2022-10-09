package com.example.anti_vampireboxapp;

import com.example.anti_vampireboxapp.box.AntiVampBox;

import java.util.ArrayList;

/**
 * This static class keeps track of the Anti-Vampire boxes that are connected, so we do not have to
 * keep track of the Anti-Vampire boxes in each Activity separately.
 */
public class BoxesManager {

    /**
     * Static ArrayList of all the Anti-Vampire boxes.
     */
    private static ArrayList<AntiVampBox> boxes = new ArrayList<>();

    /**
     * This method gets all Anti-Vampire boxes.
     * @return All Anti-Vampire boxes.
     */
    public static ArrayList<AntiVampBox> getBoxes() {
        return boxes;
    }

    /**
     * This method gets one box by index.
     * @param index index of list.
     * @return Anti-Vampire box at the given index.
     */
    public static AntiVampBox getBox(int index) {
        return boxes.get(index);
    }

    /**
     * Adds an Anti-Vampire box to {@link #boxes}.
     * @param box The box to be added.
     */
    public static void addBox(AntiVampBox box) {
        boxes.add(box);
    }

    /**
     * Adds an array of Anti-Vampire boxes to {@link #boxes}.
     * @param boxes Anti-Vampire boxes to be added.
     */
    public static void addAll(AntiVampBox... boxes) {
        for(AntiVampBox box : boxes) {
            addBox(box);
        }
    }

    /**
     * Returns whether all boxes are letting power through.
     * @return true if all boxes are letting power through and false otherwise.
     */
    public static boolean areAllBoxesEnabled() {
        return areAllBoxesEnabled(true);
    }

    /**
     * Returns whether all boxes are not letting power through.
     * @return true if all boxes are not letting power through and false otherwise.
     */
    public static boolean areAllBoxesDisabled() {
        return areAllBoxesEnabled(false);
    }

    /**
     * Returns whether all boxes are letting power through or not letting power through, dictated
     * by #enabled.
     * @param enabled boolean to check whether all boxes are letting power through or not.
     * @return true if all boxes are letting power through and enabled is true or if all boxes
     * are not letting power through and enabled is false. We return false otherwise.
     */
    private static boolean areAllBoxesEnabled(boolean enabled) {
        for(AntiVampBox box : boxes) {
            if(box.isCurrentEnabled() != enabled) {
                return false;
            }
        }
        return true;
    }

    /**
     * Set all boxes to let current though or not based on #enabled.
     * @param enabled if enabled is true, we set all Anti-Vampire boxes to let power through and
     * not let power through otherwise.
     */
    public static void setCurrentAllBoxes(boolean enabled) {
        for(AntiVampBox box : boxes) {
            box.setCurrentEnabled(enabled);
        }
    }

}
