package com.example.amk.lacara;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "myApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating button and what it does
        //Grabbing Button from Activity
        Button btn = (Button) findViewById(R.id.btnPantry);
        //Button is doing stuff
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Test button for the pantry!");
                Toast.makeText(getApplicationContext(), "This is Pantry!", Toast.LENGTH_LONG).show();

            }
        });
    }
}
