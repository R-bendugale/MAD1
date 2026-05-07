package com.example.facultynotesapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button facultyBtn, studentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        facultyBtn = findViewById(R.id.facultyBtn);
        studentBtn = findViewById(R.id.studentBtn);

        facultyBtn.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            i.putExtra("role", "faculty");
            startActivity(i);
        });

        studentBtn.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            i.putExtra("role", "student");
            startActivity(i);
        });
    }
}