package edu.gdou.placemange.entity;

import java.util.List;

public class SelectMsg {

    private String storey;
    private String type;
    private List<TimeCollection> timeCollection;

    public String getStorey() {
        return storey;
    }

    public void setStorey(String storey) {
        this.storey = storey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TimeCollection> getTimeCollection() {
        return timeCollection;
    }

    public void setTimeCollection(List<TimeCollection> timeCollection) {
        this.timeCollection = timeCollection;
    }

    @Override
    public String toString() {
        return "SelectMsg{" +
                "storey='" + storey + '\'' +
                ", type='" + type + '\'' +
                ", timeCollection=" + timeCollection +
                '}';
    }
}
