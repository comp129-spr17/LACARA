package com.example.amk.lacara;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

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
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem, arrayList);
        listView.setAdapter(adapter);

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Toast.makeText(CalendarActivity.this,""+date,Toast.LENGTH_SHORT).show();
                //arrayList = new ArrayList<>(Arrays.asList(items));
                String SelectedDate = date.toString();
                int len = Days.length;
                for(int i = 0; i < len; i++ )
                {
                    String Day = Days[i].toString();
                    if(SelectedDate == Day )
                    {
                        String thing = DayItem[i].toString();
                        arrayList.add(thing);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });


    }
}
