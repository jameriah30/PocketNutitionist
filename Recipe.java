package com.jameriah.pocketnutritionist;

/**
 * Created by jameriah on 6/10/17.
 */

public class Recipe {

    private String thumb_url,title,description;

    public Recipe(String thumb_url, String title, String description) {
        this.thumb_url = thumb_url;
        this.title = title;
        this.description = description;
    }

    public String getThumb_url() {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
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
}
