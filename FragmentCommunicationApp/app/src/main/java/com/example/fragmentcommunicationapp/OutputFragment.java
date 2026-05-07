package com.example.fragmentcommunicationapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OutputFragment extends Fragment {

    TextView tvOutput;

    public OutputFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_output, container, false);
        tvOutput = view.findViewById(R.id.tvOutput);
        return view;
    }

    public void displayData(String t1, String t2) {
        if (tvOutput != null) {
            tvOutput.setText("Text 1: " + t1 + "\nText 2: " + t2);
        }
    }
}
