package com.example.fat2fit.models;

import org.json.JSONException;
import org.json.JSONObject;

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

    public static JSONObject differences(User original, User updated) throws JSONException {
        JSONObject json = new JSONObject();
        if (!original.email.equals(updated.email))
            json.put("email", updated.email);
        if (!original.firstName.equals(updated.firstName))
            json.put("firstName", updated.firstName);
        if (!original.lastName.equals(updated.lastName))
            json.put("lastName", updated.lastName);
        if (!original.password.equals(updated.password))
            json.put("password", updated.password);
        if (original.role != updated.role)
            json.put("role", updated.role);
        if (original.pushupScore != updated.pushupScore)
            json.put("pushupScore", updated.pushupScore);
        if (original.situpScore != updated.situpScore)
            json.put("situpScore", updated.situpScore);
        if (original.freq != updated.freq)
            json.put("freq", updated.freq);
        if (original.height != updated.height)
            json.put("height", updated.height);
        if (original.waist != updated.waist)
            json.put("waist", updated.waist);

        return json;
    }
}
