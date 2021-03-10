package com.example.fat2fit.models;

public class GroupActivity {
    private String _id, title, description, hyperlink,
    // May want to change data type of 'created'
    created;

    public GroupActivity() {}

    public GroupActivity(
            String _id, String title, String description, String hyperlink, String created) {
        this._id = _id;
        this.title = title;
        this.description = description;
        this.hyperlink = hyperlink;
        this.created = created;
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

    public String getHyperlink() {
        return hyperlink;
    }
    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }

    public String getCreated() {
        return created;
    }
    public void setCreated(String created) {
        this.created = created;
    }
}
