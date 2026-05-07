package com.example.socialhub;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnWhatsapp, btnInstagram, btnFacebook, btnYoutube, btnTwitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWhatsapp = findViewById(R.id.btnWhatsapp);
        btnInstagram = findViewById(R.id.btnInstagram);
        btnFacebook = findViewById(R.id.btnFacebook);
        btnYoutube = findViewById(R.id.btnYoutube);
        btnTwitter = findViewById(R.id.btnTwitter);

        btnWhatsapp.setOnClickListener(v ->
                openApp("com.whatsapp", "WhatsApp"));

        btnInstagram.setOnClickListener(v ->
                openApp("com.instagram.android", "Instagram"));

        btnFacebook.setOnClickListener(v ->
                openApp("com.facebook.katana", "Facebook"));

        btnYoutube.setOnClickListener(v ->
                openApp("com.google.android.youtube", "YouTube"));

        btnTwitter.setOnClickListener(v ->
                openApp("com.twitter.android", "Twitter"));
    }

    private void openApp(String packageName, String appName) {

        PackageManager pm = getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(packageName);

        if (intent != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this,
                    appName + " is not installed",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
