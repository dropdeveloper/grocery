package com.imarahtech.grocery.activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

import com.imarahtech.grocery.R;
import com.imarahtech.grocery.fragment.IntroScreenOne;
import com.imarahtech.grocery.fragment.IntroScreenThree;
import com.imarahtech.grocery.fragment.IntroScreenTwo;
import com.imarahtech.grocery.utils.VerticalViewPager;

public class IntroActivity extends AppCompatActivity {

    private VerticalViewPager viewPager;
    MyAdapter mAdapter;
    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        videoView = (VideoView)findViewById(R.id.VideoView);
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

        viewPager = (VerticalViewPager) findViewById(R.id.viewPager);
        mAdapter = new MyAdapter(getSupportFragmentManager());
        //  CircleIndicator indicator = (CircleIndicator)findViewById(R.id.indicator);
        viewPager.setAdapter(mAdapter);
        //  indicator.setViewPager(viewPager);

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

    public static class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new IntroScreenOne();
                case 1:
                    return new IntroScreenTwo();
                case 2:
                    return new IntroScreenThree();
                default:
                    return null;
            }
        }
    }
}
