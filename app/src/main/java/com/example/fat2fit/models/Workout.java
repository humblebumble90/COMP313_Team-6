package com.example.fat2fit.models;

public class Workout {
    interface Gender {
        char MALE = 'M';
        char FEMALE = 'F';
    }

    private String _id, workoutName, videoHyperlink;
    private int age, rfm, repetition;
    private char gender;

    public Workout() {
        _id = "";
        workoutName = "";
        videoHyperlink = "";
        gender = Gender.MALE;
    }

    public Workout(
            String _id, int age, int rfm, char gender,
            String workoutName, int repetition, String videoHyperlink
    ) {
        this._id = _id;
        this.age = age;
        this.rfm = rfm;
        this.gender = gender;
        this.workoutName = workoutName;
        this.repetition = repetition;
        this.videoHyperlink = videoHyperlink;
    }

    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }

    public String getWorkoutName() {
        return workoutName;
    }
    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getVideoHyperlink() {
        return videoHyperlink;
    }
    public void setVideoHyperlink(String videoHyperlink) {
        this.videoHyperlink = videoHyperlink;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public int getRfm() {
        return rfm;
    }
    public void setRfm(int rfm) {
        this.rfm = rfm;
    }

    public int getRepetition() {
        return repetition;
    }
    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }

    public char getGender() {
        return gender;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }
}
