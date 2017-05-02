package com.example.amk.lacara;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
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
        ArrayList<Entry> entriesp = MonthlyCat(db);

        BarChart barChart = (BarChart) findViewById(R.id.chartb);

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
        barChart.animateY(1000);
        barChart.setDescription("Monthly Expenses");
        barChart.getLegend().setWordWrapEnabled(true);

        PieChart pieChart = (PieChart) findViewById(R.id.chartp);
        PieDataSet datap = new PieDataSet(entriesp, "Categories of expenses");
        ArrayList<String> label2 = new ArrayList<String>();
        label2.add("Food");
        label2.add("Utilities");
        label2.add("Personal");
        label2.add("Activities");
        label2.add("Auto");
        label2.add("Home");
        PieData pd = new PieData(label2, datap);
        datap.setColors( ColorTemplate.COLORFUL_COLORS);
        pieChart.setData(pd);
        pieChart.animateY(1000);
        pieChart.setDescription("Item categories break down chart by year");
        pieChart.getLegend().setWordWrapEnabled(true);


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

    public ArrayList<Entry> MonthlyCat(MyDBHandler db){
        ArrayList<Entry> d = new ArrayList<>();
        String yr = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        float monthlyFood = 0;
        float monthlyU = 0;
        float MonthlyP = 0;
        float mAct= 0;
        float mAuto = 0;
        float mHome = 0;
        ArrayList<String> Month = new ArrayList<String>();
        Month.add("01");
        Month.add("02");
        Month.add("03");
        Month.add("04");
        Month.add("05");
        Month.add("06");
        Month.add("07");
        Month.add("08");
        Month.add("09");
        Month.add("10");
        Month.add("11");
        Month.add("12");
        for(int i = 0; i < Month.size(); i++)
        {
            monthlyFood += (float) db.getMonthCat(Month.get(i), yr, "Food");
            monthlyU += (float) db.getMonthCat(Month.get(i), yr, "Utilities");
            MonthlyP += (float) db.getMonthCat(Month.get(i), yr, "Personal");
            mAct += (float) db.getMonthCat(Month.get(i), yr, "Activities");
            mAuto += (float) db.getMonthCat(Month.get(i), yr, "Auto");
            mHome += (float) db.getMonthCat(Month.get(i), yr, "Home");
        }
        d.add(new Entry(monthlyFood, 0));
        d.add(new Entry(monthlyU, 1));
        d.add(new Entry(MonthlyP, 2));
        d.add(new Entry(mAct, 3));
        d.add(new Entry(mAuto, 4));
        d.add(new Entry(mHome, 5));

        return d;
    }


}

