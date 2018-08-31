package com.imarahtech.grocery.registration;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.imarahtech.grocery.R;
import com.imarahtech.grocery.activity.MainActivity;
import com.imarahtech.grocery.utils.Constants;
import com.imarahtech.grocery.utils.PreferenceHelper;

public class CustomerRegistrationActivity extends AppCompatActivity {

    private String address, city, state, country, postCode;
    private EditText Address, Street, Locality, Pincode, LandMark, Name, State;
    ImageView iv_profile;
    Button bt_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);
        getSupportActionBar().hide();

        Name = (EditText)findViewById(R.id.input_name);
        Address = (EditText)findViewById(R.id.input_house);
        Street = (EditText)findViewById(R.id.input_street);
        Locality = (EditText)findViewById(R.id.input_locality);
        LandMark = (EditText)findViewById(R.id.input_land);
        Pincode = (EditText)findViewById(R.id.input_pin);
        State = (EditText)findViewById(R.id.input_city);

        iv_profile = findViewById(R.id.iv_profile);

        Glide.with(this)
                .load(getResources().getDrawable(R.drawable.iv_no_profile))
                .apply(new RequestOptions().circleCrop().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(iv_profile);


        Intent intent = getIntent();
//        String id = intent.getStringExtra("id");
        address = intent.getStringExtra("address");
        city = intent.getStringExtra("city");
        state = intent.getStringExtra("state");
        country = intent.getStringExtra("country");
        postCode = intent.getStringExtra("post");

        Street.setText(address);
        Locality.setText(city);
        State.setText(state);
        Pincode.setText(postCode);

        bt_submit = findViewById(R.id.bt_submit);
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(CustomerRegistrationActivity.this, MainActivity.class);

                new PreferenceHelper(CustomerRegistrationActivity.this).putBoolean(Constants.REGISTER_FLAG, true);
                startActivity(ii);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    finishAffinity();
                }else {finish();}
            }
        });


    }
}
