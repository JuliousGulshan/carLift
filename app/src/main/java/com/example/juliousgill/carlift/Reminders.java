package com.example.juliousgill.carlift;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * Created by Julious Gulshan - 15969
 */

public class Reminders extends AppCompatActivity {

    //variables
    private Button cancel;

    String message;
    String repeatTime;
    String stayTime;
    String drop_item2;
    String drop_item;
    String contentText;
    String contentText2;
    long timesys;
    String formattedDate;
    long timesys2;
    String formattedDate2;
    long timestay;
    int id;
    NotificationManager mNotificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        cancel = (Button)findViewById(R.id.btn_r_cancel);

        // on click listener for button
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), HomeActivity.class);
                startActivityForResult(myIntent, 0);
                finish();
            }
        });

        Bundle extras = getIntent().getExtras();

        //String message = "";
        EditText text = (EditText) findViewById(R.id.title);
        message = text.getText().toString();

        Switch simpleSwitch = (Switch) findViewById(R.id.switch2);
        //Boolean switchState = simpleSwitch.isChecked();
        simpleSwitch.setTextOn("Yes"); // displayed text of the Switch whenever it is in checked or on state
        simpleSwitch.setTextOff("No"); // displayed text of the Switch whenever it is in unchecked i.e. off state
        //Boolean choice = simpleSwitch.getShowText();
        Boolean switchState = simpleSwitch.isChecked();


        EditText time =(EditText) findViewById(R.id.everytime);
        repeatTime= time.getText().toString();





        Spinner dropdown = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.time, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        dropdown.setAdapter(staticAdapter);

        drop_item = dropdown.getSelectedItem().toString();

        EditText time2 =(EditText) findViewById(R.id.time2);
        stayTime= time2.getText().toString();



        Spinner dropdown2 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> staticAdapter2 = ArrayAdapter
                .createFromResource(this, R.array.time, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        dropdown2.setAdapter(staticAdapter2);

        drop_item2= dropdown2.getSelectedItem().toString();

        Button b = (Button) findViewById(R.id.button);




    }

    public void setReminder(View view){
        EditText text = (EditText) findViewById(R.id.title);
        message = text.getText().toString();

        Switch simpleSwitch = (Switch) findViewById(R.id.switch2);
        //Boolean switchState = simpleSwitch.isChecked();
        simpleSwitch.setTextOn("Yes"); // displayed text of the Switch whenever it is in checked or on state
        simpleSwitch.setTextOff("No"); // displayed text of the Switch whenever it is in unchecked i.e. off state
        //Boolean choice = simpleSwitch.getShowText();
        Boolean switchState = simpleSwitch.isChecked();


        EditText time =(EditText) findViewById(R.id.everytime);
        repeatTime= time.getText().toString();


        Spinner dropdown =(Spinner)findViewById(R.id.spinner);
        drop_item = dropdown.getSelectedItem().toString();


        EditText time2 =(EditText) findViewById(R.id.time2);
        stayTime= time2.getText().toString();



        Spinner dropdown2 = (Spinner) findViewById(R.id.spinner3);
        drop_item2= dropdown2.getSelectedItem().toString();



        Calendar cal= Calendar.getInstance();


        if(drop_item2.equals("Hours"))
        {timestay=(Long.parseLong(stayTime)*60*60*1000);
            cal.add(Calendar.HOUR_OF_DAY, Integer.parseInt(stayTime));}
        else if(drop_item2.equals("Days"))
        {timestay=(Long.parseLong(stayTime)*60*60*24*1000);
            cal.add(Calendar.DAY_OF_MONTH,Integer.parseInt(stayTime));}
        else if(drop_item2.equals("Weeks"))
        {timestay=(Long.parseLong(stayTime)*60*60*1000*24*7);
            cal.add(Calendar.WEEK_OF_MONTH,Integer.parseInt(stayTime));}
        else if(drop_item2.equals("Months"))
        {timestay=(Long.parseLong(stayTime)*60*60*24*1000*30);
            cal.add(Calendar.MONTH,Integer.parseInt(stayTime));}
        else
        {timestay=(Long.parseLong(stayTime)*60*60*24*1000*365);
            cal.add(Calendar.YEAR,Integer.parseInt(stayTime));}

        //Date date = new Date(timesys);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy, hh:mm aa");



        timesys=cal.getTimeInMillis();
        cal.setTimeInMillis(timesys);
        formattedDate=dateFormatter.format(cal.getTime());

        if(repeatTime.length()!=0) {
            Calendar cal2 = Calendar.getInstance();
            cal2.setLenient(true);


            if (drop_item.equals("Hours"))
                cal2.add(Calendar.HOUR_OF_DAY, Integer.parseInt(repeatTime));
            else if (drop_item.equals("Days"))
                cal2.add(Calendar.DAY_OF_MONTH, Integer.parseInt(repeatTime));
            else if (drop_item.equals("Weeks"))
                cal2.add(Calendar.WEEK_OF_MONTH, Integer.parseInt(repeatTime));
            else if (drop_item.equals("Months"))
                cal2.add(Calendar.MONTH, Integer.parseInt(repeatTime));
            else
                cal2.add(Calendar.YEAR, Integer.parseInt(repeatTime));


            SimpleDateFormat dateFormatter2 = new SimpleDateFormat("MMM dd, yyyy, hh:mm aa");


            timesys2 = cal2.getTimeInMillis();
            cal2.setTimeInMillis(timesys2);
            formattedDate2 = dateFormatter2.format(cal2.getTime());
        }

        if(repeatTime.length()!=0){
            contentText= "Repeat every: "+repeatTime+ " "+ drop_item+" "+"\nNext at: "+formattedDate2;
            contentText2="\nEnds at: "+formattedDate; }
        else {contentText ="Ends at: "+formattedDate;
            contentText2="";}


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.color_mustard_yellow)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("REMINDER: "+message))
                        .setContentTitle("REMINDER: "+message)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(contentText+" "+contentText2))
                        .setContentText(contentText+" "+contentText2);

// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);
        //int mID=1;

// started Activity.

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);

// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        id=(int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
        mNotificationManager.notify(id, mBuilder.build());
        removeNotification(id);
        // NotificationView notifi = new NotificationView(message,repeatTime,drop_item,stayTime,drop_item2);
        Intent intent = new Intent(Reminders.this,HomeActivity.class);
        startActivity(intent);
        Toast.makeText(Reminders.this, "Reminder Set", Toast.LENGTH_SHORT).show();


    }

    private void removeNotification(final int id) {
        Handler handler = new Handler();
        long delayInMilliseconds = timestay;
        handler.postDelayed(new Runnable() {
            public void run() {
                mNotificationManager.cancel(id);
            }
        }, delayInMilliseconds);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Reminders.this, HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

}


