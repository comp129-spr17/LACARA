package com.example.amk.lacara;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        //Alison: Using this for calendar
        MaterialCalendarView materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);

        materialCalendarView.state().edit().setFirstDayOfWeek( Calendar.MONDAY );
        materialCalendarView.state().edit().setMinimumDate( CalendarDay.from(1900, 1, 1) );
        materialCalendarView.state().edit().setMaximumDate( CalendarDay.from(2100, 12, 31) );
        materialCalendarView.state().edit().setCalendarDisplayMode( CalendarMode.MONTHS );
        materialCalendarView.state().edit().commit();

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Toast.makeText(CalendarActivity.this,""+date,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
