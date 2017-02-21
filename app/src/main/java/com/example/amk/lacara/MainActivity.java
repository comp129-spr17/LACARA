package com.example.amk.lacara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "myApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText entEmailLogin = (EditText) findViewById(R.id.entEmailLogin);
        final EditText entPassLogin = (EditText) findViewById(R.id.entPassLogin);

        final Button btLogin = (Button) findViewById(R.id.btLogin);
        final Button btReg = (Button) findViewById(R.id.btReg);
        final Button camera = (Button) findViewById(R.id.camera);

        btReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent regIntent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(regIntent);
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent cameraIntent = new Intent(MainActivity.this, CameraActivity.class);
                MainActivity.this.startActivity(cameraIntent);
            }
        });
    }
}
