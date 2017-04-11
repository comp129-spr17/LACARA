package com.example.amk.lacara;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;
import android.support.v7.widget.Toolbar;





public class UserSettings extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    EditText name;
    EditText budget;
    EditText password;
    EditText email;
   // EditText Notification;

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
        t=(ToggleButton)findViewById(R.id.theme);
        r= (RelativeLayout) findViewById(R.id.layout);

        t.setOnCheckedChangeListener(this);

        name = (EditText) findViewById(R.id.name);
        budget = (EditText) findViewById(R.id.budget);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        //notifications = (Ed) findViewById(R.id.notifications);

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
        SettingsData user = new SettingsData(Double.parseDouble(temp),name.getText().toString().toString(),password.getText().toString().toString());
        dbHandler.addSettings(user);
    }
}
