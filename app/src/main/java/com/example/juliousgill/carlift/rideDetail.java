package com.example.juliousgill.carlift;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juliousgill.carlift.Model.OfferRide;
import com.example.juliousgill.carlift.Model.OfferRideList;

import java.util.List;
/**
 * Created by Julious Gulshan - 15969
 */
public class rideDetail extends AppCompatActivity  {

    //variables
    TextView tx_origin, tx_destination, tx_date_time, tx_phone, tx_car;

    Button bookRideBtn;
    Button cancelBtn;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ride_detail);

        // book btn and canel button
        bookRideBtn = (Button) findViewById(R.id.btn_bokRide);
        cancelBtn = (Button) findViewById(R.id.btn_dcancel);

        // onclick listeners
        cancelBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), searchForRide.class);
                startActivityForResult(myIntent, 0);
                finish();
            }

        });


        bookRideBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {

                rideBooked();

            }
        });


        // getting the details of list item in new intent
        tx_origin = (TextView) findViewById(R.id.tx_origin);
        tx_destination = (TextView) findViewById(R.id.tx_destination);
        tx_date_time = (TextView) findViewById(R.id.tx_date_time);
        tx_phone = (TextView) findViewById(R.id.tx_phone);
        tx_car = (TextView) findViewById(R.id.tx_car);

        Intent myIntent = getIntent();

        String or = myIntent.getStringExtra("origin");
        String des = myIntent.getStringExtra("destination");
        String dt = myIntent.getStringExtra("date_time");
        String ph = myIntent.getStringExtra("phone");
        String ca = myIntent.getStringExtra("car");

        tx_origin.setText(or);
        tx_destination.setText(des);
        tx_date_time.setText(dt);
        tx_phone.setText(ph);
        tx_car.setText(ca);

        //calling function on click on given phone number
        tx_phone.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String phone_no = tx_phone.getText().toString().replaceAll("-", "");
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phone_no));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(callIntent);
            }
        });


    }

    private void rideBooked() {

        if(bookRideBtn.isPressed()){

            Toast.makeText(getBaseContext(), "Your Ride Is Booked!" , Toast.LENGTH_SHORT ).show();
        }

        else bookRideBtn.setText("Not available");

        return;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(rideDetail.this, HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}



