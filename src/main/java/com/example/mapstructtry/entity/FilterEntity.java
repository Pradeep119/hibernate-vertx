package com.example.mapstructtry.entity;

public class FilterEntity {
    private String id;
    private String name;
    private String color;
    private String entry;

    public FilterEntity(){}

    public FilterEntity(String id, String name, String color, String entry) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.entry = entry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
