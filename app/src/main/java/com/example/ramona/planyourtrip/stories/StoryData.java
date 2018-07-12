package com.example.ramona.planyourtrip.stories;

/**
 * Created by Ramona on 7/2/2018.
 */

public class StoryData {
    private String description;
    private String title;
    private String imagePath;
    private String facebook;
    private String instagram;

    public StoryData(String description, String title, String imagePath, String facebook, String instagram) {
        this.description = description;
        this.title = title;

        this.imagePath = imagePath;
        this.facebook = facebook;
        this.instagram = instagram;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
