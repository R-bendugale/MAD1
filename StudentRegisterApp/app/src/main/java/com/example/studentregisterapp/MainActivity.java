package com.example.studentregisterapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etName, etRoll, etCourse;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etRoll = findViewById(R.id.etRoll);
        etCourse = findViewById(R.id.etCourse);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {

            String name = etName.getText().toString();
            String roll = etRoll.getText().toString();
            String course = etCourse.getText().toString();

            if (name.isEmpty() || roll.isEmpty() || course.isEmpty()) {
                Toast.makeText(MainActivity.this,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT).show();
            } else {

                Intent intent = new Intent(MainActivity.this,
                        SecondActivity.class);

                intent.putExtra("name", name);
                intent.putExtra("roll", roll);
                intent.putExtra("course", course);

                startActivity(intent);
            }
        });
    }
}
