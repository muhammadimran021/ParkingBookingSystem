package com.example.muhammadimran.parkingbookingsystem.ParkingAreas;

/**
 * Created by muhammad imran on 1/27/2017.
 */

public class TimeModel {
    private String uuid;
    private String date;
    private String time;
    private String mint;

    public TimeModel() {
    }

    public TimeModel(String uuid, String date, String time, String mint) {
        this.uuid = uuid;
        this.date = date;
        this.time = time;
        this.mint = mint;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public String getMint() {
        return mint;
    }

    public void setMint(String mint) {
        this.mint = mint;
    }
}
