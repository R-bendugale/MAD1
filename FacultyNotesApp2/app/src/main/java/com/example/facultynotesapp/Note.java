package com.example.facultynotesapp;

public class Note {
    public String title, content, facultyName;

    public Note() {
        // Required for Firebase
    }

    public Note(String title, String content, String facultyName) {
        this.title = title;
        this.content = content;
        this.facultyName = facultyName;
    }

    // ADD THESE METHODS BELOW:
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getFacultyName() { return facultyName; }
}