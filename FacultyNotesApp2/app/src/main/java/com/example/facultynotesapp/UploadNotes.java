package com.example.facultynotesapp;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

public class UploadNotes extends AppCompatActivity {

    EditText title, content;
    Button uploadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        uploadBtn = findViewById(R.id.uploadBtn);

        // 1. Define your specific Singapore Database URL
        String databaseUrl = "https://facultynotesapp-e5c63-default-rtdb.asia-southeast1.firebasedatabase.app/";

        uploadBtn.setOnClickListener(v -> {

            String titleStr = title.getText().toString();
            String contentStr = content.getText().toString();

            if (titleStr.isEmpty() || contentStr.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            String name = email != null ? email.split("@")[0] : "Faculty";

            HashMap<String, String> map = new HashMap<>();
            map.put("title", titleStr);
            map.put("content", contentStr);
            map.put("facultyName", name);

            // 2. Use the databaseUrl to ensure data goes to the right server
            // Using lowercase "notes" to keep it consistent
            FirebaseDatabase.getInstance(databaseUrl)
                    .getReference("notes")
                    .push()
                    .setValue(map)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                            title.setText("");
                            content.setText("");
                        } else {
                            Toast.makeText(this, "Upload Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        });
    }
}