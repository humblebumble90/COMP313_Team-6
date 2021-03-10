package com.example.fat2fit.models;

public class Group {
    private String _id;
    private String name;
    private User coach;
    private User[] members;
    private GroupActivity[] activities;

    public Group() {}

    public Group(String _id, String name, User coach, User[] members, GroupActivity[] activities) {
        this._id = _id;
        this.name = name;
        this.coach = coach;
        this.members = members;
        this.activities = activities;
    }

    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public User getCoach() {
        return coach;
    }
    public void setCoach(User coach) {
        this.coach = coach;
    }

    public User[] getMembers() {
        return members;
    }
    public void setMembers(User[] members) {
        this.members = members;
    }
    public User getMember(int index) {
        return members[index];
    }

    public GroupActivity[] getActivities() {
        return activities;
    }
    public void setActivities(GroupActivity[] activities) {
        this.activities = activities;
    }
    public GroupActivity getActivity(int index) {
        return activities[index];
    }
}
