package com.example.amk.lacara;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

public class UserSettings extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

// add input variables here


ToggleButton t;
RelativeLayout r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
        t=(ToggleButton)findViewById(R.id.theme);
        r= (RelativeLayout) findViewById(R.id.layout);

        t.setOnCheckedChangeListener(this);
    }

//save user settings button
    public void saveInfo (View view){
        SharedPreferences sharedPref = getSharedPreferences("userSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean t) {
        if(t)
        {
            r.setBackgroundColor(Color.BLACK);
        }
        else
        {
            r.setBackgroundColor(Color.WHITE);
        }
    }
}
