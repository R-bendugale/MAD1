package com.example.progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    Button b, b1;
    ProgressBar p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = findViewById(R.id.button);
        b1 = findViewById(R.id.button1);
        p = findViewById(R.id.progress);

        // Show simple ProgressBar
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.setVisibility(View.VISIBLE);
            }
        });

        // Show ProgressDialog
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProgressDialog progressBar = new ProgressDialog(MainActivity.this);
                progressBar.setCancelable(true);
                progressBar.setMessage("File downloading...");
                progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressBar.setProgress(0);
                progressBar.setMax(100);
                progressBar.show();
            }
        });
    }
}