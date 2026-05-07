package com.example.fragmentcommunicationapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
        implements InputFragment.OnDataSendListener {

    OutputFragment outputFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create fragment objects
        InputFragment inputFragment = new InputFragment();
        outputFragment = new OutputFragment();

        // Load fragments into containers
        getSupportFragmentManager().beginTransaction()
                .add(R.id.inputContainer, inputFragment)
                .add(R.id.outputContainer, outputFragment)
                .commit();
    }

    // This method receives data from InputFragment
    @Override
    public void sendData(String text1, String text2) {
        outputFragment.displayData(text1, text2);
    }
}
