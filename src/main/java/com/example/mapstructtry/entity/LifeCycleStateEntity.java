package com.example.mapstructtry.entity;

public class LifeCycleStateEntity {
    private String component;
    private String state;

    public LifeCycleStateEntity(){}

    public LifeCycleStateEntity(String component, String state) {
        this.component = component;
        this.state = state;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
