package com.example.amk.lacara;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;


public class UserSettings extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    EditText name;
    EditText budget;
    EditText password;
    EditText email;

    MyDBHandler dbHandler;



    int dark = Color.parseColor("#7A8AA1");
    ToggleButton t;
    RelativeLayout r;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
        Toolbar SettingsToolbar = (Toolbar) findViewById(R.id.settingsTB);
        setSupportActionBar(SettingsToolbar);

        r= (RelativeLayout) findViewById(R.id.layout);
        dbHandler = new MyDBHandler(this, null, null, 1);
        t.setOnCheckedChangeListener(this);

        name = (EditText) findViewById(R.id.name);
        budget = (EditText) findViewById(R.id.budget);
        //notifications = (Ed) findViewById(R.id.notifications);

        final Button logoutButton = (Button) findViewById(R.id.logoutButton);
        dbHandler.userExist();
        ArrayList<String> info = new ArrayList<String>();
        info.addAll(dbHandler.getUser());
        name.setText(info.get(0));
        budget.setText(info.get(1));
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logoutIntent = new Intent(UserSettings.this, MainActivity.class);
                UserSettings.this.startActivity(logoutIntent);
            }
        });

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean t) {
        if(t)
        {
            r.setBackgroundColor(Color.WHITE);
        }
        else
        {
            r.setBackgroundColor(Color.parseColor("#7A8AA1"));
        }
    }
    public void saveInfo (View view){
        String temp = budget.getText().toString();
        String n = name.getText().toString();
        dbHandler.updateUser("_Name", n);
        dbHandler.updateUser("_budget", temp);
    }


}
