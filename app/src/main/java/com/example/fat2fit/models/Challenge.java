package com.example.fat2fit.models;

public class Challenge {
    private String _id, title, description;
    private double distance;
    // private Reward reward;


    public Challenge(String _id, String title, String description, double distance) {
        this._id = _id;
        this.title = title;
        this.description = description;
        this.distance = distance;
    }

    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
}
