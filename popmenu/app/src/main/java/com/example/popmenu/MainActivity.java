package com.example.popmenu;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.clickBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(MainActivity.this, button);

                // attach menu file
                popupMenu.getMenuInflater().inflate(R.menu.popupmenu, popupMenu.getMenu());

                // click handling
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        Toast.makeText(MainActivity.this,
                                "You Clicked " + menuItem.getTitle(),
                                Toast.LENGTH_SHORT).show();

                        return true;
                    }
                });

                popupMenu.show();
            }
        });
    }
}