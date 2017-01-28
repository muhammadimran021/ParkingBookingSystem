package com.example.muhammadimran.parkingbookingsystem.Models;

/**
 * Created by muhammad imran on 1/27/2017.
 */

public class TimeModel {
    private String uuid;
    private String date;
    private String start_time;
    private String end_time;
    private String parking;

    public TimeModel() {
    }


    public TimeModel(String uuid, String date, String time, String mint, String parking) {
        this.uuid = uuid;
        this.date = date;
        this.start_time = time;
        this.end_time = mint;
        this.parking = parking;
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

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }
}
