package com.imarahtech.grocery.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.VideoView;

import com.imarahtech.grocery.R;

public class LoginActivity extends AppCompatActivity {

    private VideoView videoView;
    private Button Login, bt_skip;
    private EditText PhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        PhoneNumber = (EditText)findViewById(R.id.et_phone);
        videoView = (VideoView)findViewById(R.id.VideoView);
        Login = (Button)findViewById(R.id.bt_login);
        bt_skip = findViewById(R.id.bt_skip);

        // Uri uri= Uri.parse("android.resource://"+getPackageName()+"/"+R.);

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


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum = PhoneNumber.getText().toString();
                Intent i = new Intent(getApplicationContext(), VerificationActivity.class);
                i.putExtra("phone",phoneNum.trim());
                startActivity(i);
            }
        });

        bt_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    finishAffinity();
                }else {finish();}
            }
        });

    }


    @Override
    protected void onRestart() {
        Uri uri= Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.intro);

        //  Uri video = Uri.parse("android.resource://com.example.imarah.freshlemon/raw/intro");
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
        {
            @Override    public void onPrepared(MediaPlayer mediaPlayer)
            {
                mediaPlayer.setLooping(true);
                mediaPlayer.setVolume(0f, 0f);
            }
        });

        super.onRestart();
    }
}
