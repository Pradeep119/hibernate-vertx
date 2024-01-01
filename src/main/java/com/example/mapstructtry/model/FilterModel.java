package com.example.mapstructtry.model;

public class FilterModel {
    private String name;
    private String color;
    private String entry;

    public FilterModel(){}

    public FilterModel(String name, String color, String entry) {
        this.name = name;
        this.color = color;
        this.entry = entry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }
}
