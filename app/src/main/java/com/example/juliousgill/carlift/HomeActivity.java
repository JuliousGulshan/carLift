package com.example.juliousgill.carlift;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Julious Gulshan - 15969
 */

public class HomeActivity extends AppCompatActivity {

    //variables
    private Button searchRide;
    private Button offRide;
    private Button logout;
    private Button reminder;
    private Button weather;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        searchRide = (Button)findViewById(R.id.btn_search);
        offRide =(Button)findViewById(R.id.btn_ofrRide);
        reminder =(Button)findViewById(R.id.btn_Rem);
        weather =(Button)findViewById(R.id.btn_weather);
        logout = (Button)findViewById(R.id.btn_Logout);

        // on click listener for button
        searchRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), searchForRide.class);
                startActivityForResult(myIntent, 0);
                finish();
            }
        });

        offRide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), offerForRide.class);
                startActivityForResult(myIntent, 0);
                finish();
            }

        });

       reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), Reminders.class);
                startActivityForResult(myIntent, 0);
                finish();
            }

        });

        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), WeatherMain.class);
                startActivityForResult(myIntent, 0);
                finish();
            }

        });


        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
                finish();
            }

        });



    }
}
