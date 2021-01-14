package com.example.audioprocessing;

public class AudioFile {
    private String title, location;

    public AudioFile(String title, String location) {
        this.title = title;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }
}
