package com.imarahtech.grocery.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.imarahtech.grocery.R;
import com.imarahtech.grocery.activity.LoginActivity;


public class IntroScreenThree extends Fragment {
    private Button Start;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.intro_screen_three, container, false);

        Start = (Button)v.findViewById(R.id.bt_start);

        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), LoginActivity.class);
                startActivity(i);
            }
        });
        return v;


    }

}