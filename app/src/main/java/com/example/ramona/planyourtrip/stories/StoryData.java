package com.example.ramona.planyourtrip.stories;

/**
 * Created by Ramona on 7/2/2018.
 */

public class StoryData {
    private String description;

    private String imagePath;

    public StoryData(String imagePath, String description) {
        this.imagePath = imagePath;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }
}
