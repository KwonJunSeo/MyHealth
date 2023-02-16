package com.example.myhealth_final;

public class Viewholder {

    private int health_id;
    private String health_name;
    private String part;
    Boolean selected = false;

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }


    public void setHealth_id(int health_id) {
        this.health_id = health_id;
    }

    public void setHealth_name(String health_name) {
        this.health_name = health_name;
    }

    public int getHealth_id() {
        return this.health_id;
    }

    public String getHealth_name() {
        return this.health_name;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

}
