package com.example.facultynotesapp;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.*;

import java.util.ArrayList;

public class ViewNotes extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);

        listView = findViewById(R.id.listView);

        ArrayList<String> list = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                list
        );

        listView.setAdapter(adapter);

        // 1. Define the Singapore Database URL (Matching UploadNotes.java)
        String databaseUrl = "https://facultynotesapp-e5c63-default-rtdb.asia-southeast1.firebasedatabase.app/";

        // 2. Initialize with the URL and use lowercase "notes"
        FirebaseDatabase.getInstance(databaseUrl)
                .getReference("notes")
                .addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        list.clear();

                        for (DataSnapshot data : snapshot.getChildren()) {
                            // Fetch data as a Note object
                            Note note = data.getValue(Note.class);

                            if (note != null) {
                                // Use the fields from your Note class
                                String text = "Title: " + note.getTitle() +
                                        "\nContent: " + note.getContent() +
                                        "\nBy: " + note.getFacultyName();

                                list.add(text);
                            }
                        }
                        // Refresh the UI
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Toast.makeText(
                                ViewNotes.this,
                                "Error: " + error.getMessage(),
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                });
    }
}