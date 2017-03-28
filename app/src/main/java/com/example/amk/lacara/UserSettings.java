package com.example.amk.lacara;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UserSettings extends AppCompatActivity {

// add input variables here




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        //add inputs here
    }

//save user settings button
    public void saveInfo (View view){
        SharedPreferences sharedPref = getSharedPreferences("userSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

    }

}
