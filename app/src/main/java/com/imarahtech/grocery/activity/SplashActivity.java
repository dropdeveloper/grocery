package com.imarahtech.grocery.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

import com.imarahtech.grocery.R;
import com.imarahtech.grocery.utils.Constants;
import com.imarahtech.grocery.utils.PreferenceHelper;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        videoView = (VideoView) findViewById(R.id.VideoView);
        //   Uri uri= Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.intro);

        Uri video = Uri.parse("android.resource://com.imarahtech.grocery/raw/intro");
        videoView.setVideoURI(video);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
        {
            @Override    public void onPrepared(MediaPlayer mediaPlayer)
            {
                mediaPlayer.setLooping(true);
                mediaPlayer.setVolume(0f, 0f);
            }
        });

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            // @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                boolean register_flag = new PreferenceHelper(SplashActivity.this).getBoolean(Constants.REGISTER_FLAG);
                if (register_flag){
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                }else {
                    Intent i = new Intent(SplashActivity.this, IntroActivity.class);
                    startActivity(i);
                }
                finish();


            }
        }, SPLASH_TIME_OUT);
    }
}
