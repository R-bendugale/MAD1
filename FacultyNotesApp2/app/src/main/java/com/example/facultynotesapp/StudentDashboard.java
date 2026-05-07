package com.example.facultynotesapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class StudentDashboard extends AppCompatActivity {

    Button viewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        viewBtn = findViewById(R.id.viewBtn);

        viewBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, ViewNotes.class));
        });
    }
}