package com.example.juliousgill.carlift;

import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


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
public class searchForRide extends AppCompatActivity {

    DatabaseReference databaseOfferRide;

    ListView listViewOfferRide;

    List<OfferRide> offerRideList;

    EditText editDestination;
    ArrayAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_ride);

        databaseOfferRide = FirebaseDatabase.getInstance().getReference("OfferRide");

        listViewOfferRide = (ListView) findViewById(R.id.listViewOfferRide);

        // list view Clickable to open new intent
        listViewOfferRide.setClickable(true);
        listViewOfferRide.setAdapter(new ArrayAdapter<OfferRide>(this,R.layout.row_layout));
        listViewOfferRide.setOnItemClickListener(new AdapterView.OnItemClickListener()
                 {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id ) {

                        TextView tx_origin = (TextView) view.findViewById(R.id.tx_origin);
                        TextView tx_destination = (TextView) view.findViewById(R.id.tx_destination);
                        TextView tx_date_time = (TextView) view.findViewById(R.id.tx_date_time);
                        TextView tx_phone = (TextView) view.findViewById(R.id.tx_phone);
                        TextView tx_car = (TextView) view.findViewById(R.id.tx_car);

                        String origin = tx_origin.getText().toString();
                        String destination = tx_destination.getText().toString();
                        String date_time = tx_date_time.getText().toString();
                        String phone = tx_phone.getText().toString();
                        String car = tx_car.getText().toString();

                    Intent  i=new Intent(searchForRide.this,rideDetail.class);

                        i.putExtra("origin", origin);
                        i.putExtra("destination", destination);
                        i.putExtra("date_time", date_time);
                        i.putExtra("phone", phone);
                        i.putExtra("car", car);

                    startActivity(i);}
                 }
        );



        offerRideList = new ArrayList<>();

        final Button pickLocation = (Button)findViewById(R.id.btn_pick);
        final EditText editDestination = (EditText) findViewById(R.id.edit_Dest);


        final Button SearchRideBtn = (Button) findViewById(R.id.btn_Search);
       // final Button BookRideBtn = (Button) findViewById(R.id.btn_booked);


        final Button cancelBtn = (Button)findViewById(R.id.btn_SCancel);

        cancelBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), HomeActivity.class);
                startActivityForResult(myIntent, 0);
                finish();
            }

        });

        pickLocation.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Locations.class);
                startActivity(i);
            }

        });

/*
*/
        assert SearchRideBtn != null;
        SearchRideBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {

                editDestination.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

                        searchForRide.this.adapter.getFilter().filter(cs);

                    }

                    @Override
                    public void beforeTextChanged(CharSequence cs, int arg1, int arg2, int arg3)  {

                    }


                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

            }
        });
    }
        @Override
        protected void onStart() {
            super.onStart();


                databaseOfferRide.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    offerRideList.clear();

                    for (DataSnapshot offerRideSnapshot : dataSnapshot.getChildren()) {

                        OfferRide offerRide = offerRideSnapshot.getValue(OfferRide.class);

                        offerRideList.add(offerRide);
                    }

                    OfferRideList adapter = new OfferRideList(searchForRide.this, offerRideList);
                    listViewOfferRide.setAdapter(adapter);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(searchForRide.this, HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

    }
