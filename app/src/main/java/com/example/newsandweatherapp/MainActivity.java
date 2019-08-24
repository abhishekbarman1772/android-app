package com.example.newsandweatherapp;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Range;
import android.view.Window;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
     Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+5:30"));

    private static final String TAG = "MainActivity";
    private ArrayList<Drawable> weatherIcons = new ArrayList<>();
    private ArrayList<String> daysOfWeek = new ArrayList<>();
    private ArrayList<String> minimum = new ArrayList<>();
    private ArrayList<String> maximum = new ArrayList<>();
    private RelativeLayout relativeLayout;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter adapter;
    private WebView webView;
    Range<Integer> nr = Range.create(0,5);
    Range<Integer> dayr = Range.create(18,24);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        webView = findViewById(R.id.webView);
        webView.setBackgroundColor(Color.TRANSPARENT); //for gif without background
        webView.loadUrl("file:///android_asset/cloud.html");
        final LinearLayout l = findViewById(R.id.upperLayout);
        final LinearLayout b=findViewById(R.id.bottomLayout);
        relativeLayout=findViewById(R.id.listItemLayout);




            //to get current date and time

            Date currentLocalTime = cal.getTime();
            DateFormat date = new SimpleDateFormat("HH:mm a");
            // you can get seconds by adding  "...:ss" to it
            date.setTimeZone(TimeZone.getDefault());

            String localTime = date.format(currentLocalTime);

            //Get the hour from the calendar
      Toast.makeText(getApplicationContext(),localTime,Toast.LENGTH_LONG).show();

        Timer timer = new Timer();
        long interval = (1000) ; // 1 sec

        timer.schedule( new TimerTask() {


            public void run() {
                while (true) {
                    try {
                        int hour =cal.get(Calendar.HOUR_OF_DAY);
                        int min =cal.get(Calendar.MINUTE);

                    if (hour >= 6 && (hour <= 11 && min<=59))              // Check if hour is between 6 am and 12
                    {

                        l.setBackgroundResource(R.drawable.morning);
                        b.setBackgroundResource(R.drawable.li_morning);
    //            relativeLayout.setBackgroundResource(R.drawable.li_morning);
                    }
                     else if (hour >= 12 && (hour <= 15 && min<=59))  //check if hour is between 12 and 4pm
                    {

                        l.setBackgroundResource(R.drawable.afternoon);
                        b.setBackgroundResource(R.drawable.li_afternoon);
    //            relativeLayout.setBackgroundResource(R.drawable.li_evening);
                    }

                       else if(hour >= 16 && (hour <= 17 && min<=59))  //check if hour is between 4pm and 6pm
                    {

                        l.setBackgroundResource(R.drawable.evening);
                        b.setBackgroundResource(R.drawable.li_evening);
    //            relativeLayout.setBackgroundResource(R.drawable.li_afternoon);
                    }
                    else if( dayr.contains(hour) || nr.contains(hour)&& min <=59)  //check if hour is between 6pm and 6am

                    {

                        l.setBackgroundResource(R.drawable.night);
                        b.setBackgroundResource(R.drawable.li_night);

                    } }

                    catch (Exception e)
                    {
                        // Throwing an exception
                        Toast.makeText(getApplicationContext(),"background theme exception",Toast.LENGTH_SHORT).show();
                    }
                }
            }

        }, 0, interval);





        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        String dat = day + "/" + (month+1) + "/" + year;
        switch (month+1) {
            case 1:
                dat = "January, " + day + ", " + year;
                break;
            case 2:
                dat = "February, " + day + ", " + year;
                break;
            case 3:
                dat = "March, " + day + ", " + year;
                break;
            case 4:
                dat = "April, " + day + ", " + year;
                break;
            case 5:
                dat = "May, " + day + ", " + year;
                break;
            case 6:
                dat = "June, " + day + ", " + year;
                break;
            case 7:
                dat = "July, " + day + ", " + year;
                break;
            case 8:
                dat = "August, " + day + ", " + year;
                break;
            case 9:
                dat = "September, " + day + ", " + year;
                break;
            case 10:
                dat = "October, " + day + ", " + year;
                break;
            case 11:
                dat = "November, " + day + ", " + year;
                break;
            case 12:
                dat = "December, " + day + ", " + year;
                break;
                default:
                    break;
        }
        TextView dateView = findViewById(R.id.date);
        dateView.setText(dat);

        //to get weekday
        int weekDay = c.get(Calendar.DAY_OF_WEEK);
        String dayOfWeek = "";
        switch (weekDay) {
            case 1:
                dayOfWeek = "Sunday";
                break;
            case 2:
                dayOfWeek = "Monday";
                break;
            case 3:
                dayOfWeek = "Tuesday";
                break;
            case 4:
                dayOfWeek = "Wednesday";
                break;
            case 5:
                dayOfWeek = "Thursday";
                break;
            case 6:
                dayOfWeek = "Friday";
                break;
            case 7:
                dayOfWeek = "Saturday";
                break;
        }
        TextView dayView = findViewById(R.id.day);
        dayView.setText(dayOfWeek);
        Log.i("Know", "Addition started");
        setDrawables();
        Log.i("Know", "Drawables added");
        setWeekDays();
        Log.i("Know", "Weekdays added");
        setMinTemperatures();
        Log.i("Know", "min temp added");
        setMaxTemperatures();
        Log.i("Know", "max temp added");
        initRecyclerView();
        Log.i("Know", "recycler view initialized");

//        recyclerView.setHasFixedSize(true);
    }

    private void setDrawables() {
        weatherIcons.add(ContextCompat.getDrawable(this, R.drawable.sun));
        weatherIcons.add(ContextCompat.getDrawable(this, R.drawable.cloud));
        weatherIcons.add(ContextCompat.getDrawable(this, R.drawable.cloud_sun_solid));
        weatherIcons.add(ContextCompat.getDrawable(this, R.drawable.lightning));
        weatherIcons.add(ContextCompat.getDrawable(this, R.drawable.rain));
        weatherIcons.add(ContextCompat.getDrawable(this, R.drawable.rain_moon));
        weatherIcons.add(ContextCompat.getDrawable(this, R.drawable.rain_sun));
        weatherIcons.add(ContextCompat.getDrawable(this, R.drawable.smog));
        weatherIcons.add(ContextCompat.getDrawable(this, R.drawable.snowflake));
        weatherIcons.add(ContextCompat.getDrawable(this, R.drawable.wind));
    }

    private void setWeekDays() {
        daysOfWeek = new ArrayList<>(Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"));
    }

    private void setMinTemperatures() {
        minimum.add("Min: 19°C");
        minimum.add("Min: 19°C");
        minimum.add("Min: 19°C");
        minimum.add("Min: 19°C");
        minimum.add("Min: 19°C");
        minimum.add("Min: 19°C");
        minimum.add("Min: 19°C");
    }

    private void setMaxTemperatures() {
        maximum.add("Max: 27°C");
        maximum.add("Max: 27°C");
        maximum.add("Max: 27°C");
        maximum.add("Max: 27°C");
        maximum.add("Max: 27°C");
        maximum.add("Max: 27°C");
        maximum.add("Max: 27°C");
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(daysOfWeek, weatherIcons, minimum, maximum, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
    }
}
