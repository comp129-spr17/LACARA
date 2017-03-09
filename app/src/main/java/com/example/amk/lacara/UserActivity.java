package com.example.amk.lacara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        final EditText entEmailLogin = (EditText) findViewById(R.id.entEmailLogin);

        Intent intent = getIntent();
        String useremail = intent.getStringExtra("useremail");

        String message = useremail + "is logged in!";
        final Button camera = (Button) findViewById(R.id.BTcamera);
        final Button graphs = (Button) findViewById(R.id.BTGraphs);
        final Button calendar = (Button) findViewById(R.id.BTCalendar);
        final Button manual = (Button) findViewById(R.id.BTManual);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent cameraIntent = new Intent(UserActivity.this, CameraActivity.class);
                UserActivity.this.startActivity(cameraIntent);
            }
        });
        graphs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent graphsIntent = new Intent(UserActivity.this, GraphsActivity.class);
                UserActivity.this.startActivity(graphsIntent);
            }
        });
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent calendarIntent = new Intent(UserActivity.this, GraphsActivity.class);
                UserActivity.this.startActivity(calendarIntent);
            }
        });
        manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent manualIntent = new Intent(UserActivity.this, ManualEnterActivity.class);
                UserActivity.this.startActivity(manualIntent);
            }
        });
    }
}
