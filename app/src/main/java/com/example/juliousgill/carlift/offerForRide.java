package com.example.juliousgill.carlift;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juliousgill.carlift.Model.OfferRide;
import com.example.juliousgill.carlift.Model.OfferRideList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julious Gulshan - 15969
 */

public class offerForRide extends AppCompatActivity {

    //variable
    EditText originText;
    EditText destinationText;
    EditText timeText;
    EditText phoneText;
    EditText carNo;
    Button offerRideBtn;
    Button cancelBtn;

    DatabaseReference databaseOfferRide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_for_ride);

        databaseOfferRide = FirebaseDatabase.getInstance().getReference("OfferRide");

        originText = (EditText) findViewById(R.id.editOfr_Source);
        destinationText = (EditText) findViewById(R.id.editOfr_Dest);
        timeText = (EditText) findViewById(R.id.editOfr_Time);
        phoneText = (EditText) findViewById(R.id.edit_Phone);
        carNo = (EditText) findViewById(R.id.editText_CarNo);


        offerRideBtn = (Button) findViewById(R.id.btn_ofr);
        cancelBtn = (Button) findViewById(R.id.btn_Cancel);


        cancelBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), HomeActivity.class);
                startActivityForResult(myIntent, 0);
                finish();
            }

        });


        offerRideBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                addOfferRide();

            }
        });

    }

    //method
    private void addOfferRide() {
        String OriginTxt = originText.getText().toString().trim();
        String DestinationTxt = destinationText.getText().toString();
        String TimeTxt = timeText.getText().toString().trim();
        String PhoneTxt = phoneText.getText().toString().trim();
        String CarTxt = carNo.getText().toString().trim();

        if (!TextUtils.isEmpty(OriginTxt)) {

            String id = databaseOfferRide.push().getKey();

            OfferRide offerRide = new OfferRide(id, OriginTxt, DestinationTxt, TimeTxt, PhoneTxt, CarTxt);

            databaseOfferRide.child(id).setValue(offerRide);

            Toast.makeText(this, "Ride Added", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "You must enter origin", Toast.LENGTH_LONG).show();
        }

        if (!TextUtils.isEmpty(DestinationTxt)) {


        } else {
            Toast.makeText(this, "You must enter destination", Toast.LENGTH_LONG).show();
        }

        if (!TextUtils.isEmpty(TimeTxt)) {

        } else {
            Toast.makeText(this, "You must enter time", Toast.LENGTH_LONG).show();
        }

        if (!TextUtils.isEmpty(PhoneTxt)) {

        } else {
            Toast.makeText(this, "You must enter phoneNo.", Toast.LENGTH_LONG).show();
        }

        if (!TextUtils.isEmpty(CarTxt)) {

        } else {
            Toast.makeText(this, "You must enter carNo.", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(offerForRide.this, HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}
