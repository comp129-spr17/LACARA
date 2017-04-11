package com.example.amk.lacara;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.text.*;
import android.util.Log;

public class CalendarActivity extends AppCompatActivity {
    private ArrayList<String> arrayList;
    //private ArrayList<String> arrayListTemp;
    private ArrayAdapter<String> adapter;
    MyDBHandler dbHandler;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        dbHandler = new MyDBHandler(this, null, null, 1);

        Toolbar CalToolbar = (Toolbar) findViewById(R.id.CalTB);
        setSupportActionBar(CalToolbar);

        //Alison: Using this for calendar
        MaterialCalendarView materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);

        materialCalendarView.state().edit().setFirstDayOfWeek( Calendar.MONDAY );
        materialCalendarView.state().edit().setMinimumDate( CalendarDay.from(1900, 1, 1) );
        materialCalendarView.state().edit().setMaximumDate( CalendarDay.from(2100, 12, 31) );
        materialCalendarView.state().edit().setCalendarDisplayMode( CalendarMode.MONTHS );
        materialCalendarView.state().edit().commit();

        ListView listView = (ListView)findViewById(R.id.listItems);
        final String[] items = {"Event:"};
        final String[] Days = {"CalendarDay{2017-2-14}", "CalendarDay{2017-2-15}"};
        final String[] DayItem = {"Apple","Orange","Eggs"};
        arrayList = new ArrayList<>(Arrays.asList(items));
        //arrayListTemp = new ArrayList();
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem, arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new testList());

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
                String temp = df.format(date.getDate());
                arrayList.clear();

                arrayList.add(temp);

                //ArrayList<String> arrayListTemp = dbHandler.singleResult(temp);
                //arrayList.addAll(arrayListTemp);
                arrayList.addAll(dbHandler.singleResult(temp));
              /*  Log.d("tag", arrayList.get(0).toString());
                Log.d("tag", arrayList.get(1).toString());
                Log.d("tag", arrayList.get(2).toString());*/
                adapter.notifyDataSetChanged();
            }
        });
    }

    class testList implements AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            /*ViewGroup vg = (ViewGroup)view;
            TextView ty = (TextView)vg.findViewById(R.id.txtitem);
            String temp = "" + position;
            Log.d("test", temp);
            Toast.makeText(CalendarActivity.this, ty.getText().toString(), Toast.LENGTH_SHORT).show();*/
            if(position != 0) {
                Intent settingsIntent = new Intent(CalendarActivity.this, ItemViews.class);
                settingsIntent.putExtra("item", arrayList.get(position));
                CalendarActivity.this.startActivity(settingsIntent);
            }
        }
    }
}

