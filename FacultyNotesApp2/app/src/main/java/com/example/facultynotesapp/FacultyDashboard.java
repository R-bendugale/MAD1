package com.example.facultynotesapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class FacultyDashboard extends AppCompatActivity {

    Button uploadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_dashboard);

        uploadBtn = findViewById(R.id.uploadBtn);

        uploadBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, UploadNotes.class));
        });
    }
}