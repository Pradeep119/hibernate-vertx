package com.example.mapstructtry.dto;

public class FilterDto {
    private String id;
    private String color;
    private String entry;
    private String state;

    public FilterDto(){}

    public FilterDto(String id, String color, String entry) {
        this.id = id;
        this.color = color;
        this.entry = entry;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
