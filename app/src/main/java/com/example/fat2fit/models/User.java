package com.example.fat2fit.models;

public class User {
    public interface Roles {
        char END_USER = 'U';
        char ADMIN = 'A';
        char CUSTOMER_REP = 'C';
    }

    private String _id, email, firstName, lastName, password;

    private char role = 'U';

    // NOTE: Not sure if these data types are correct,
    // please feel free to change them
    private float height, waist;
    private int pushupScore, situpScore, freq;

    public User() {}

    public User(String _id, String email, String firstName, String lastName, String password) {
        this._id = _id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public char getRole() {
        return role;
    }
    public void setRole(char role) {
        this.role = role;
    }

    public float getHeight() {
        return height;
    }
    public void setHeight(float height) {
        this.height = height;
    }

    public float getWaist() {
        return waist;
    }
    public void setWaist(float waist) {
        this.waist = waist;
    }

    public int getPushupScore() {
        return pushupScore;
    }
    public void setPushupScore(int pushupScore) {
        this.pushupScore = pushupScore;
    }

    public int getSitupScore() {
        return situpScore;
    }
    public void setSitupScore(int situpScore) {
        this.situpScore = situpScore;
    }

    public int getFreq() {
        return freq;
    }
    public void setFreq(int freq) {
        this.freq = freq;
    }
}
