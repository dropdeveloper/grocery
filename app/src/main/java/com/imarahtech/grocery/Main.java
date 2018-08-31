package com.imarahtech.grocery;

import android.app.Application;

import com.imarahtech.grocery.utils.TypeFaceUtils;

public class Main extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TypeFaceUtils.overrideFont(getApplicationContext(), "SERIF", "fonts/PlayFairDisplay.otf");

    }
}
