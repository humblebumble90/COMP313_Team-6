package com.example.fat2fit.models;

public class Reward {
    private String _id, title, company;

    public Reward() {}

    public Reward(String _id, String title, String company) {
        this._id = _id;
        this.title = title;
        this.company = company;
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

    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
}
