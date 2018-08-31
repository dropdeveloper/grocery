package com.imarahtech.grocery.registration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.imarahtech.grocery.R;

public class DealerRegistartionActivity extends AppCompatActivity {

    private String address, city, state, country, postCode;
    private EditText Address, Street, Locality, Pincode, LandMark, Name, State;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_registartion);
        getSupportActionBar().hide();

        Name = (EditText)findViewById(R.id.input_name);
        Address = (EditText)findViewById(R.id.input_house);
        Street = (EditText)findViewById(R.id.input_street);
        Locality = (EditText)findViewById(R.id.input_locality);
        LandMark = (EditText)findViewById(R.id.input_land);
        Pincode = (EditText)findViewById(R.id.input_pin);
        State = (EditText)findViewById(R.id.input_city);


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

    }
}
