package com.example.amk.lacara;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar CalToolbar = (Toolbar) findViewById(R.id.CalTB);
        setSupportActionBar(CalToolbar);
    }
}
