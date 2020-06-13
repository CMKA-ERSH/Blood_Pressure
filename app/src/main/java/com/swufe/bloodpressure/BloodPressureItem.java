package com.swufe.bloodpressure;

public class BloodPressureItem {
    private String highBP;
    private String lowBP;
    private String heartRate;
    private String date;
    private int id;

    public BloodPressureItem() {
        super();
        this.highBP = "";
        this.lowBP = "";
        this.heartRate = "";
        this.date = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BloodPressureItem(String highBP, String lowBP, String heartRate, String date) {
        this.highBP = highBP;
        this.lowBP = lowBP;
        this.heartRate = heartRate;
        this.date = date;
    }

    public String getHighBP() {
        return highBP;
    }

    public void setHighBP(String highBP) {
        this.highBP = highBP;
    }

    public String getLowBP() {
        return lowBP;
    }

    public void setLowBP(String lowBP) {
        this.lowBP = lowBP;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
