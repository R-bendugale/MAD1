package com.example.facultynotesapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button loginBtn;
    private FirebaseAuth auth;
    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize UI Elements
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();

        // Retrieve role from Intent
        role = getIntent().getStringExtra("role");
        if (role == null) {
            role = "student"; // Default fallback
        }

        loginBtn.setOnClickListener(v -> {
            // .trim() is CRITICAL to prevent the "malformed" error from image_e69ed4.png
            String txt_email = email.getText().toString().trim();
            String txt_password = password.getText().toString().trim();

            if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                Toast.makeText(LoginActivity.this, "Please enter all details", Toast.LENGTH_SHORT).show();
            } else if (txt_password.length() < 6) {
                Toast.makeText(LoginActivity.this, "Password must be >= 6 chars", Toast.LENGTH_SHORT).show();
            } else {
                loginUser(txt_email, txt_password);
            }
        });
    }

    private void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Success: Navigate to the correct dashboard
                        Intent intent;
                        if ("faculty".equalsIgnoreCase(role)) {
                            intent = new Intent(LoginActivity.this, FacultyDashboard.class);
                        } else {
                            intent = new Intent(LoginActivity.this, StudentDashboard.class);
                        }

                        // Prevent user from going back to login screen
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    } else {
                        // This will now show the REAL reason for failure (e.g., "User not found")
                        String error = task.getException() != null ? task.getException().getMessage() : "Unknown Error";
                        Toast.makeText(LoginActivity.this, "Login Failed: " + error, Toast.LENGTH_LONG).show();
                    }
                });
    }
}