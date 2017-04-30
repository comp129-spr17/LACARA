package com.example.amk.lacara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import android.widget.Toast;

import android.util.Log;

public class ItemViews extends AppCompatActivity {
    TextView areaDisplay;
    EditText name;
    EditText date;
    EditText price;
    EditText location;
    int result;
    ArrayList<String> infoList = new ArrayList<String>();
    MyDBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_views);
        String id = getIntent().getStringExtra("id");
        name = (EditText) findViewById(R.id.displayItem);
        price = (EditText) findViewById(R.id.displayPrice);
        //price.addTextChangedListener(new NumberTextWatcher(price, "#,###"));
        location = (EditText) findViewById(R.id.displayLocation);
        dbHandler = new MyDBHandler(this, null, null, 1);
        result = Integer.valueOf(id);

        infoList.addAll(dbHandler.itemViewDisplay(result));
        DatePickerInt(infoList.get(2));
        name.setText(infoList.get(0));
        location.setText(infoList.get(1));
        price.setText(infoList.get(3));
        final Button edit = (Button) findViewById(R.id.editInfo);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean stop = false;
                if(name.length() == 0)
                {
                    Toast.makeText(getApplicationContext(), "Name Empty!", Toast.LENGTH_LONG).show();
                    name.setText(infoList.get(0));
                    stop = true;
                }

                if(location.length() == 0)
                {
                    Toast.makeText(getApplicationContext(), "Location Empty!", Toast.LENGTH_LONG).show();
                    location.setText(infoList.get(1));
                    stop = true;
                }

                if(price.length() == 0)
                {
                    Toast.makeText(getApplicationContext(), "Price Empty!", Toast.LENGTH_LONG).show();
                    price.setText(infoList.get(3));
                    stop = true;
                }

                if(stop)
                {
                    return;
                }

                if(ifChange(name.getText().toString(),infoList.get(0)))
                {
                    dbHandler.updateSingleDate("_recipename", name.getText().toString(), result);
                }

               if(ifChange(location.getText().toString(),infoList.get(1)))
                {
                    dbHandler.updateSingleDate("_location", location.getText().toString(), result);
                }

                if(ifChange(date.getText().toString(),infoList.get(2)))
                {
                    dbHandler.updateSingleDate("_date", date.getText().toString(), result);
                }

                if(ifChange(price.getText().toString(),infoList.get(3)))
                {
                    dbHandler.updateSingleDate("_price", price.getText().toString(), result);
                }
            }
        });
    }

    boolean ifChange(String x, String x1)
    {
        if(x != x1)
        {
            return true;
        }

        return false;
    }

    public void DatePickerInt(String date)
    {
        DatePicker mDatePicker = (DatePicker) findViewById(R.id.datePicker2);
        String[] tokens = date.split("-");
        int year = Integer.parseInt(tokens[2]);
        int month = Integer.parseInt(tokens[0]);
        int day = Integer.parseInt(tokens[1]);
        mDatePicker.init(year, month, day, null);
        Log.d("testdate", tokens[0] + "-" + tokens[1] + "-" + tokens[2]);
    }
}
