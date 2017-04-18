package com.example.amk.lacara;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManualEnterActivity extends AppCompatActivity {

    EditText item;
    EditText location;
    EditText price;
    EditText date;
    TextView areaDisplay;
    MyDBHandler dbHandler;
    //String[] itemsTest = AddPrefUtility.getItemList(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manualenter);
        Toolbar ManualToolbar = (Toolbar) findViewById(R.id.ManualTB);
        setSupportActionBar(ManualToolbar);
        dbHandler = new MyDBHandler(this, null, null, 1);
        CatSpinner();

        item = (EditText) findViewById(R.id.itemName);
        location = (EditText) findViewById(R.id.locationInput);
        price = (EditText) findViewById(R.id.priceInput);
        date = (EditText) findViewById(R.id.entDate);
        areaDisplay = (TextView) findViewById(R.id.areaDisplay);


        printDatabase();
    }

    //save entered text to button saveInfo
        public void saveInfo (View view){
            String temp = price.getText().toString();
            String temp2 = date.getText().toString();
            Data recipe = new Data(item.getText().toString().toString(),location.getText().toString().toString(),Double.parseDouble(temp));
            dbHandler.addRecipe(recipe);
            printDatabase();
        }
        //create spinner for item categories
        public void CatSpinner()
        {
            Spinner dropdown = (Spinner) findViewById(R.id.categories);
            List<String> list = new ArrayList<String>();
            list.add("Food");
            list.add("Utilities");
            list.add("Personal");
            list.add("Activities");
            list.add("Auto");
            list.add("Home");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
            dropdown.setAdapter(adapter);
        }

        //button to test to display saved data
        public void displayInfo (View view){
            /*
            SharedPreferences sharedPref = getSharedPreferences ("itemInfo", Context.MODE_PRIVATE);

            String iname = sharedPref.getString("itemName", "");
            String lname = sharedPref.getString("locName", "");
            String pname = sharedPref.getString("priceName", "");
            areaDisplay.setText(iname + " " + lname + " " + pname);


            //I'm trying random stuff right here lol
            String itemTest2 = itemsTest.toString();
            areaDisplay.setText(itemTest2);
            */

    }




    public void printDatabase()
    {
        String dbString = dbHandler.databaseToString();
        areaDisplay.setText(dbString);
        item.setText("");
        location.setText("");
        price.setText("");
        date.setText("");
    }

}



