package com.example.amk.lacara;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;

public class GraphsActivity extends AppCompatActivity{
    MyDBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);
        db = new MyDBHandler(this, null, null, 1);
        ArrayList<BarEntry> entries = MonthlyData(db);

        BarChart barChart = (BarChart) findViewById(R.id.chart);

        BarDataSet dataset = new BarDataSet(entries, "Your monthly expenses");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        labels.add("August");
        labels.add("September");
        labels.add("October");
        labels.add("November");
        labels.add("December");

        BarData data = new BarData(labels, dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setData(data);
        barChart.animateY(3000);
        barChart.setDescription("Monthly Expenses");
    }
    public ArrayList<BarEntry> MonthlyData(MyDBHandler DB)
    {
        String yr = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        float Jan = (float) DB.getMonthTotal("01",yr);
        float Feb = (float) DB.getMonthTotal("02",yr);
        float Mar = (float) DB.getMonthTotal("03",yr);
        float Apr = (float) DB.getMonthTotal("04",yr);
        float May = (float) DB.getMonthTotal("05",yr);
        float Jun = (float) DB.getMonthTotal("06",yr);
        float Jul = (float) DB.getMonthTotal("07",yr);
        float Aug = (float) DB.getMonthTotal("08",yr);
        float Sep = (float) DB.getMonthTotal("09",yr);
        float Oct = (float) DB.getMonthTotal("10",yr);
        float Nov = (float) DB.getMonthTotal("11",yr);
        float Dec = (float) DB.getMonthTotal("12",yr);

        ArrayList<BarEntry> Entries = new ArrayList<>();
        Entries.add(new BarEntry(Jan, 0));
        Entries.add(new BarEntry(Feb, 1));
        Entries.add(new BarEntry(Mar, 2));
        Entries.add(new BarEntry(Apr, 3));
        Entries.add(new BarEntry(May, 4));
        Entries.add(new BarEntry(Jun, 5));
        Entries.add(new BarEntry(Jul, 6));
        Entries.add(new BarEntry(Aug, 7));
        Entries.add(new BarEntry(Sep, 8));
        Entries.add(new BarEntry(Oct, 9));
        Entries.add(new BarEntry(Nov, 10));
        Entries.add(new BarEntry(Dec, 11));

        return Entries;
    }

}

