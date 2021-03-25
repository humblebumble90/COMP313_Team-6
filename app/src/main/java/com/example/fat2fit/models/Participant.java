package com.example.fat2fit.models;

public class Participant {
    // Use like an enum
    public interface State {
        char ACTIVE = 'A';
        char COMPLETED = 'C';
        char UNFINISHED = 'U';
        char DROPPED = 'D';
    }

    private String _id, checkpoint;
    private double progress;
    private char state = State.ACTIVE;
    private Challenge challenge;
    // private User user;

    public Participant() {}

    public Participant(
            String _id, String checkpoint, double progress,
            char state, Challenge challenge) {
        this._id = _id;
        this.checkpoint = checkpoint;
        this.progress = progress;
        this.state = state;
        this.challenge = challenge;
    }

    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCheckpoint() {
        return checkpoint;
    }
    public void setCheckpoint(String checkpoint) {
        this.checkpoint = checkpoint;
    }

    public double getProgress() {
        return progress;
    }
    public void setProgress(double progress) {
        this.progress = progress;
    }

    public char getState() {
        return state;
    }
    public void setState(char state) {
        this.state = state;
    }

    public Challenge getChallenge() {
        return challenge;
    }
    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }
}
