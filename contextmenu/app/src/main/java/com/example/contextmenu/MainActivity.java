package com.example.contextmenu;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    RelativeLayout r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);
        r = findViewById(R.id.rel);

        registerForContextMenu(textView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        // Title like in image
        menu.setHeaderTitle("Choose a color");

        // Add items (clean list style)
        menu.add(0, 1, 0, "Yellow");
        menu.add(0, 2, 1, "Gray");
        menu.add(0, 3, 2, "Cyan");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 1:
                r.setBackgroundColor(Color.YELLOW);
                return true;

            case 2:
                r.setBackgroundColor(Color.GRAY);
                return true;

            case 3:
                r.setBackgroundColor(Color.CYAN);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
}