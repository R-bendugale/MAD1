package com.example.simpleintent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    Button btnSms, btnCall, btnSettings, btnNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSms = findViewById(R.id.btnSms);
        btnCall = findViewById(R.id.btnCall);
        btnSettings = findViewById(R.id.btnSettings);
        btnNetwork = findViewById(R.id.btnNetwork);

        // 📩 Send SMS
        btnSms.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:9876543210"));
            intent.putExtra("sms_body", "Hello from SimpleIntent App");
            startActivity(intent);
        });

        // 📞 Make Call
        btnCall.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:9876543210"));

            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE}, 1);
                return;
            }

            startActivity(intent);
        });

        // ⚙️ Open Settings
        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            startActivity(intent);
        });

        // 🌐 Show Network Details (Modern Safe Version)
        btnNetwork.setOnClickListener(v -> {

            ConnectivityManager cm =
                    (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

            if (cm != null) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    Network network = cm.getActiveNetwork();
                    if (network == null) {
                        Toast.makeText(this, "No Internet Connection",
                                Toast.LENGTH_LONG).show();
                        return;
                    }

                    NetworkCapabilities capabilities =
                            cm.getNetworkCapabilities(network);

                    if (capabilities != null &&
                            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {

                        Toast.makeText(this,
                                "Internet Connected",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this,
                                "No Internet Connection",
                                Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(this,
                            "Internet Check Not Supported",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
