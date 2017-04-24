package com.example.amk.lacara;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
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
        t=(ToggleButton)findViewById(R.id.theme);
        r= (RelativeLayout) findViewById(R.id.layout);
        dbHandler = new MyDBHandler(this, null, null, 1);
        t.setOnCheckedChangeListener(this);

        name = (EditText) findViewById(R.id.name);
        budget = (EditText) findViewById(R.id.budget);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        //notifications = (Ed) findViewById(R.id.notifications);

        final Button logoutButton = (Button) findViewById(R.id.logoutButton);
        final Button disBudget = (Button) findViewById(R.id.disBudget);

        disBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double temp = dbHandler.getBudget();
                String currentBudget = "$" + String.valueOf(temp);
                Log.d("Budget", currentBudget);
                AlertDialog.Builder alert = new AlertDialog.Builder(UserSettings.this);
                alert.setMessage(currentBudget);
                alert.setCancelable(false);
                alert.setPositiveButton(
                        "Understand",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                alert.setNegativeButton(
                        "Don't Care",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = alert.create();
            }
        });

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
        double b = Double.parseDouble(temp);
        Log.d("1","############");
        Log.d("2",temp);
        String n = name.getText().toString();
        String p = password.getText().toString();
        Log.d("3",n);
        Log.d("4",p);
        SettingsData user = new SettingsData(b, n, p);
        Log.d("5","========");
        Log.d("HERE",user.get_budget()+" "+user.get_name()+" "+user.get_password());
        dbHandler.addSettings(user);
    }
}
