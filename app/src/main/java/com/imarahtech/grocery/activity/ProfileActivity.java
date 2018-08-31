package com.imarahtech.grocery.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.imarahtech.grocery.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profile");

        init();
    }

    private void init() {

        ImageView imageView = findViewById(R.id.iv_pro);
        Glide.with(this)
                .load("http://static3.uk.businessinsider.com/image/5592ee48dd08951c488b45f7-1190-625/the-rock-is-now-the-highest-paid-actor-in-the-world--heres-who-he-beat-out.jpg")
                .apply(RequestOptions.circleCropTransform())
                .into(imageView);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_profile, menu);
//        return true;
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
