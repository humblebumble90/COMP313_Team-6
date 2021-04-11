package com.example.fat2fit.models;

public class Challenge {
    // Use like an enum
    public interface State {
        char AVAILABLE = 'A';
        char HIDDEN = 'H';
        char FINISHED = 'F';
    }

    private String _id, title, description, closes;
    private double distance;
    private char state = State.AVAILABLE;
//    private Reward reward;

    public Challenge() {}

    public Challenge(String _id, String title, String description, double distance, char state) {
        this._id = _id;
        this.title = title;
        this.description = description;
        this.distance = distance;
        this.state = state;
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

    public String getCloses() {
        return closes;
    }
    public void setCloses(String closes) {
        this.closes = closes;
    }

    public char getState() {
        return state;
    }
    public void setState(char state) {
        this.state = state;
    }

//    public Reward getReward() {
//        return reward;
//    }
//
//    public void setReward(Reward reward) {
//        this.reward = reward;
//    }
}
