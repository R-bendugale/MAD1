package com.example.fragmentcommunicationapp;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class InputFragment extends Fragment {

    EditText et1, et2;
    Button btnSend;
    OnDataSendListener listener;

    public interface OnDataSendListener {
        void sendData(String t1, String t2);
    }

    public InputFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnDataSendListener) {
            listener = (OnDataSendListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_input, container, false);

        et1 = view.findViewById(R.id.et1);
        et2 = view.findViewById(R.id.et2);
        btnSend = view.findViewById(R.id.btnSend);

        btnSend.setOnClickListener(v -> {
            String t1 = et1.getText().toString();
            String t2 = et2.getText().toString();
            listener.sendData(t1, t2);
        });

        return view;
    }
}
