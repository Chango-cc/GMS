package edu.gdou.placemange.entity;

import java.util.List;

public class TimeCollection {

    private String date;
    private List<String> timelist;
    private List<PlaceAvailable> keepList;
    private PlaceAvailable placeAvailable;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getTimelist() {
        return timelist;
    }

    public void setTimelist(List<String> timelist) {
        this.timelist = timelist;
    }

    public List<PlaceAvailable> getKeepList() {
        return keepList;
    }

    public void setKeepList(List<PlaceAvailable> keepList) {
        this.keepList = keepList;
    }

    public PlaceAvailable getPlaceAvailable() {
        return placeAvailable;
    }

    public void setPlaceAvailable(PlaceAvailable placeAvailable) {
        this.placeAvailable = placeAvailable;
    }

    @Override
    public String toString() {
        return "TimeCollection{" +
                "date='" + date + '\'' +
                ", timelist=" + timelist +
                ", keepList=" + keepList +
                ", placeAvailable=" + placeAvailable +
                '}';
    }
}
