package com.example.studentregisterapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    TextView tvDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvDetails = findViewById(R.id.tvDetails);

        String name = getIntent().getStringExtra("name");
        String roll = getIntent().getStringExtra("roll");
        String course = getIntent().getStringExtra("course");

        String details =
                "Registered Student Details\n\n" +
                        "Name: " + name + "\n" +
                        "Roll No: " + roll + "\n" +
                        "Course: " + course;

        tvDetails.setText(details);
    }
}
