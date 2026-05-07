package com.example.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();

        // Left Fragment
        fm.beginTransaction()
                .replace(R.id.left_container, MyFragment.newInstance("Hello World!"))
                .commit();

        // Right Fragment
        MyFragment rightFragment = MyFragment.newInstance("Hello");
        Bundle args = new Bundle();
        args.putBoolean("isRight", true); // flag to use right layout
        rightFragment.setArguments(args);

        fm.beginTransaction()
                .replace(R.id.right_container, rightFragment)
                .commit();
    }
}
