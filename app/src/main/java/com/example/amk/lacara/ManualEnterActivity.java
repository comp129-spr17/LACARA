package com.example.amk.lacara;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class ManualEnterActivity extends AppCompatActivity {

     EditText item;
     EditText location;
     EditText price;
     TextView areaDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manualenter);
        Toolbar ManualToolbar = (Toolbar) findViewById(R.id.ManualTB);
        setSupportActionBar(ManualToolbar);

        item = (EditText) findViewById(R.id.itemName);
        location = (EditText) findViewById(R.id.locationInput);
        price = (EditText) findViewById(R.id.priceInput);
        areaDisplay = (TextView) findViewById(R.id.areaDisplay);
    }

    //save entered text to button saveInfo
        public void saveInfo (View view){
        SharedPreferences sharedPref = getSharedPreferences ("itemInfo", Context.MODE_PRIVATE );

        SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("itemName", item.getText().toString());
            editor.putString("locName", location.getText().toString());
            editor.putString("priceName", price.getText().toString());
            editor.apply();

            Toast.makeText(this, "Saved!", Toast.LENGTH_LONG).show();
        }

        //button to test to display saved data
        public void displayInfo (View view){
            SharedPreferences sharedPref = getSharedPreferences ("itemInfo", Context.MODE_PRIVATE);

            String iname = sharedPref.getString("itemName", "");
            String lname = sharedPref.getString("locName", "");
            String pname = sharedPref.getString("priceName", "");
            areaDisplay.setText(iname + " " + lname + " " + pname);





    }


    public void buttonOnClick(View v) {

    }
}



