package com.example.optionmenu;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RelativeLayout currentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentLayout = findViewById(R.id.a);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.android) {
            currentLayout.setBackgroundColor(Color.RED);

        } else if (id == R.id.java) {
            currentLayout.setBackgroundColor(Color.GREEN);

        } else if (id == R.id.kotlin) {
            currentLayout.setBackgroundColor(Color.BLUE);
        }

        return true;
    }
}