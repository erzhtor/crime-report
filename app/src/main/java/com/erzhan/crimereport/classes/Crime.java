package com.erzhan.crimereport.classes;

/**
 * Created by Erzhan on 8-Nov 14.
 */
public class Crime {

    private int id;
    private String description;
    private int category;
    private String date;
    private String time;
    private int policeReport;
    private long latitude;
    private long longitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int isPoliceReport() {
        return policeReport;
    }

    public void setPoliceReport(int policeReport) {
        this.policeReport = policeReport;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }


}
