package com.example.amk.lacara;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import android.util.Log;

public class ItemViews extends AppCompatActivity {
    TextView areaDisplay;
    TextView name;
    TextView date;
    TextView price;
    ArrayList<String> infoList = new ArrayList<String>();
    MyDBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_views);
        String[] temp = getIntent().getStringExtra("item").split(" ");
        areaDisplay = (TextView) findViewById(R.id.displayTest);
        name = (TextView) findViewById(R.id.displayName);
        date = (TextView) findViewById(R.id.displayDate);
        price = (TextView) findViewById(R.id.displayPrice);
        dbHandler = new MyDBHandler(this, null, null, 1);
        Log.d("test", temp[0]);
        int result = Integer.parseInt(temp[0]);

        infoList.addAll(dbHandler.itemViewDisplay(result));
        name.setText(infoList.get(0));
        date.setText(infoList.get(2));
        price.setText(infoList.get(3));

    }
}
