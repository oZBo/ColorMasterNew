package com.braincollaboration.colormaster.model;

public class LibraryColorObject {

    private int color;
    private String colorName;

    public LibraryColorObject(int color, String colorName) {
        this.color = color;
        this.colorName = colorName;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
