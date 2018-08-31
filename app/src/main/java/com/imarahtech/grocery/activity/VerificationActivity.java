package com.imarahtech.grocery.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.imarahtech.grocery.R;
import com.imarahtech.grocery.registration.GetLocationActivity;

public class VerificationActivity extends AppCompatActivity {

    private VideoView videoView;
    private EditText ET_OTP1, ET_OTP2, ET_OTP3, ET_OTP4;
    private Button SubmitButtonl;
    private LinearLayout ButtonBackground;
    private TextView NumberView;
    //  @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        ButtonBackground = (LinearLayout)findViewById(R.id.buttonBack);
        videoView = (VideoView)findViewById(R.id.VideoView);
        // Uri uri= Uri.parse("android.resource://"+getPackageName()+"/"+R.);
        //   ButtonBackground.setBackgroundColor(R.color.trans_otp);
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
        NumberView = (TextView)findViewById(R.id.tv_phone);
        Intent i = getIntent();
        String phone = i.getStringExtra("phone");
        NumberView.setText(phone);


        ET_OTP1= (EditText)findViewById(R.id.otp_1);
        ET_OTP2 = (EditText)findViewById(R.id.otp_2);
        ET_OTP3 = (EditText)findViewById(R.id.otp_3);
        ET_OTP4 = (EditText)findViewById(R.id.otp_4);
        SubmitButtonl = (Button)findViewById(R.id.bt_submit);

        ET_OTP1.addTextChangedListener(new GenericTextWatcher(ET_OTP1));
        ET_OTP2.addTextChangedListener(new GenericTextWatcher(ET_OTP2));
        ET_OTP3.addTextChangedListener(new GenericTextWatcher(ET_OTP3));
        ET_OTP4.addTextChangedListener(new GenericTextWatcher(ET_OTP4));




//        SubmitButtonl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(ET_OTP1.getText().toString().equals("1")){
//                    Intent i = new Intent(getApplicationContext(), GetLocationActivity.class);
//                    i.putExtra("userType", 1);
//                    startActivity(i);
//                }else if(ET_OTP1.getText().toString().equals("2")){
//                    Intent i = new Intent(getApplicationContext(), GetLocationActivity.class);
//                    i.putExtra("userType", 2);
//                    startActivity(i);
//                }else if(ET_OTP1.getText().toString().equals("3")){
//                    Intent i = new Intent(getApplicationContext(), GetLocationActivity.class);
//                    i.putExtra("userType", 3);
//                    startActivity(i);
//                }else if(ET_OTP1.getText().toString().equals("4")){
//                    Intent i = new Intent(getApplicationContext(), GetLocationActivity.class);
//                    i.putExtra("userType", 4);
//                    startActivity(i);
//                }
//
//
//            }
//        });


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

    public class GenericTextWatcher implements TextWatcher
    {
        private View view;
        private GenericTextWatcher(View view)
        {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // TODO Auto-generated method stub
            String text = editable.toString();
            switch(view.getId())
            {

                case R.id.otp_1:
                    if(text.length()==1)
                        ET_OTP2.requestFocus();
                    break;
                case R.id.otp_2:
                    if(text.length()==1)
                        ET_OTP3.requestFocus();
                    break;
                case R.id.otp_3:
                    if(text.length()==1)
                        ET_OTP4.requestFocus();
                    break;
                case R.id.otp_4:
                    if(text.length()==1){
                        Intent i = new Intent(getApplicationContext(), GetLocationActivity.class);
                        switch (ET_OTP1.getText().toString()) {
                            case "1": {
                                i.putExtra("userType", 1);
                                break;
                            }
                            case "2": {
                                i.putExtra("userType", 2);
                                break;
                            }
                            case "3": {
                                i.putExtra("userType", 3);
                                break;
                            }
                            case "4": {
                                i.putExtra("userType", 4);
                                break;
                            }
                        }
                        startActivity(i);
                    }
                    break;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }
    }
}
