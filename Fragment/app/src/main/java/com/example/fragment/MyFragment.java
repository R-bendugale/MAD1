package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {

    private String text;

    public static MyFragment newInstance(String text) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString("text", text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(getArguments().getBoolean("isRight") ?
                R.layout.fragment_right : R.layout.fragment_left, container, false);

        TextView tv = view.findViewById(getArguments().getBoolean("isRight") ?
                R.id.textViewRight : R.id.textViewLeft);

        if (getArguments() != null) {
            String msg = getArguments().getString("text");
            tv.setText(msg);
        }

        return view;
    }
}
