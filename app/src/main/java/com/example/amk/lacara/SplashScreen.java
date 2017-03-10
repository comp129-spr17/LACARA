package com.example.amk.lacara;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.widget.ImageView;


public class SplashScreen extends AppCompatActivity {

    //wait time
    private final int splash_display_length = 2500;

    //called when activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView silogo = (ImageView) findViewById(R.id.slgView);
            silogo.setImageResource(R.drawable.suivrelogo);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashScreen.this,UserActivity.class);
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        }, splash_display_length);

    }
}
