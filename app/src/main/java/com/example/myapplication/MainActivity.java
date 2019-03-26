package com.example.myapplication;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Display d = getWindowManager().getDefaultDisplay();
//        Point size = new Point();
//        Toast.makeText(getApplicationContext(),( "Width:" + size. + "Height :" + size.y ),Toast.LENGTH_LONG).show();
    }
}
